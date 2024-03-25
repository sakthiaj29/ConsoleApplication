package com.sakthi.interviewpanel.managecandidate;

import java.util.List;
import java.util.Scanner;

import com.sakthi.interviewpanel.datalayer.InterviewPanalDataBase;
import com.sakthi.interviewpanel.model.Candidate;

public class ManageCandidateView {

	private ManageCandidateModel manageCandidateModel;
	private Scanner scanner = new Scanner(System.in);
	private final byte ADD_CANDIDATE = 1;
	private final byte SHOW_ALL_CANDIDATE = 2;
	private final byte UPDATE_CANDIDATE = 3;
	private final byte RESULT = 4;
	private final byte REMOVE_CANDIDATE = 5;
	private final byte EXIT = 0;

	public ManageCandidateView() {
		manageCandidateModel = new ManageCandidateModel(this);
	}

	public void init() {
		byte choice = -1;
		System.out.println("\n <-----------------> Candidate Page <----------------->");
		System.out.println(
				"\n 1)Add candidate\n\n 2)Show all candidate\n\n 3)Update candidate\n\n 4)Result\n\n 5)Remove candidate\n\n 4)Exit");
		System.out.print("\n Enter your choice : ");
		choice = scanner.nextByte();
		if (choice == ADD_CANDIDATE) {
			addCandidate();
		} else if (choice == SHOW_ALL_CANDIDATE) {
			onShowAllCandidate();
		} else if (choice == UPDATE_CANDIDATE) {
			updateCandidate();
		} else if (choice == RESULT) {
			showResult();
		} else if (choice == REMOVE_CANDIDATE) {
			onRemoveCandidate();
		} else if (choice == EXIT) {
			System.out.println("\n ~ ~ ~ ~ ~ ~ ~ Thank You! ~ ~ ~ ~ ~ ~ ~");
			System.exit(0);
		} else {
			System.out.println("Invalid choice, Pleace enter valid choice.");
			init();
		}
	}

	private void showResult() {
		System.out.print("Enter number of candidate you want : ");
		int numberOfCandidate = scanner.nextInt();
		int existNumberOfCandidate = InterviewPanalDataBase.getInstance().getNumberOfCandidate();

		if (0 < numberOfCandidate && numberOfCandidate <= existNumberOfCandidate) {
			List<Candidate> candidateList = InterviewPanalDataBase.getInstance()
					.getSelectedCandidateList(numberOfCandidate);
			System.out.println("\n\t_____________ View Candidate ______________");
			System.out.printf("\n  %-15s\t %-12s\t %-20s\t %-12s\t %s", "Candidate Name", "Candidate Id",
					"Candidate Email", "Candidate Mark", "Interview Status");
			System.out.print("\n  ----------------------------------------------------------------------------------------");

			for (Candidate candidate : candidateList) {
				System.out.printf("\n  %-15s\t %-12d\t %-20s\t %-12d\t %s", candidate.getName(), candidate.getId(),
						candidate.getEmailId(), candidate.getMark(), "Selected");
			}

			System.out.println("\n\nDo you want to send email for selected candidate? \nType Yes/No");
			String choice = scanner.next();

			if (choice.equalsIgnoreCase("yes")) {
				System.out.println("\n\t\t * * * * * * * Email send Successfully * * * * * * *");
				System.exit(0);
			} else {
				init();
			}

		} else {
			System.err.println("\t\t\t Invalid count");
			System.out.println("Do you want to continue? \nType Yes/No");
			String choice = scanner.next();
			if (choice.equalsIgnoreCase("yes")) {
				showResult();
			} else if (choice.equalsIgnoreCase("no")) {
				init();
			} else {
				System.out.println("\nInvalid choice, Please enter valid choice.\n");
				showResult();
			}
		}
	}

	private void onShowAllCandidate() {
		List<Candidate> candidateList = InterviewPanalDataBase.getInstance().getCandidateList();
		if (candidateList.size() > 0) {
			System.out.println("\n\t_____________ View Candidate ______________");
			System.out.printf("\n  %-15s\t %-12s\t %-20s\t %-10s", "Candidate Name", "Candidate Id", "Candidate Email",
					"Interview Status");
			System.out.print("\n  ------------------------------------------------------------------------------");
			for (Candidate candidate : candidateList) {
				System.out.printf("\n  %-15s\t %-12d\t %-20s\t %-10s", candidate.getName(), candidate.getId(),
						candidate.getEmailId(), candidate.getInterviewStatus());
			}
			System.out.println();
			init();
		} else {
			System.out.println("\n............................ No data fount :(");
			init();
		}
	}

	private void updateCandidate() {
		System.out.println("\n\t ______________ UpdateCandidate ______________");
		System.out.print("\nEnter candidate id : ");
		short id = scanner.nextShort();
		System.out.print("\nEnter your mark (?/10) : ");
		byte mark = scanner.nextByte();
		if (mark > 0 && mark < 11) {
			if (InterviewPanalDataBase.getInstance().updateCandidate(id, mark)) {
				System.out.println("\n............................ updated successfully :)");
				
				init();
			} else {
				System.out.println("\n............................ No data fount :(");
				init();
			}
		} else {
			System.err.println("\t\t\t Invalid mark");
			System.out.println("Do you want to continue? \nType Yes/No");
			String choice = scanner.next();
			if (choice.equalsIgnoreCase("yes")) {
				updateCandidate();
			} else if (choice.equalsIgnoreCase("no")) {
				System.out.println("\n Thanks for update candidate");
				init();
			} else {
				System.out.println("\nInvalid choice, Please enter valid choice.\n");
				updateCandidate();
			}
		}
	}

	private void onRemoveCandidate() {
		System.out.println("\n\t ______________ Remove candidate ______________");
		System.out.print("\n Enter candidate name");
		String name = scanner.next();
		manageCandidateModel.removeCandidate(name);
	}

	private void addCandidate() {
		Candidate candidate = new Candidate();
		System.out.println("\n\t ______________ Add candidate ______________");
		System.out.print("\n Enter candidate name: ");
		candidate.setName(scanner.next());
		System.out.print("\n Enter candidate email id: ");
		candidate.setEmailId(scanner.next());
		manageCandidateModel.addCandidate(candidate);
	}

	private void checkForAddNewCandidate() {
		System.out.println("\nDo you want to add more candidate? \nType Yes/No");
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("yes")) {
			addCandidate();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding candidate");
			init();
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewCandidate();
		}
	}

	public void onSuccess(Candidate candidate) {
		System.out.println("\n ............................ '" + candidate.getName() + "' added successfully :)");
		checkForAddNewCandidate();
	}

	public void onExistCandidate(Candidate candidate) {
		System.out.println("\n ............................ '" + candidate.getName() + "' already exist :|");
		checkForAddNewCandidate();
	}

	public void onRemoveSuccess(Candidate candidate) {
		System.out.println("\n ............................ '" + candidate.getName() + "' removed successfully :)");
		checkForAddNewCandidate();
	}

	public void onRemoveFaild(Candidate candidate) {
		System.out.println("\n ............................ '" + candidate.getName() + "' not exist :(");
		checkForAddNewCandidate();
	}
}
