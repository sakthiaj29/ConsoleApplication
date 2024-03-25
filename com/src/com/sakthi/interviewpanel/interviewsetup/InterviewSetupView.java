package com.sakthi.interviewpanel.interviewsetup;

import java.time.LocalDate;
import java.util.Scanner;

import com.sakthi.interviewpanel.datalayer.InterviewPanalDataBase;
import com.sakthi.interviewpanel.managecandidate.ManageCandidateView;
import com.sakthi.interviewpanel.model.Interview;

public class InterviewSetupView {

	private InterviewSetupModel interviewSetupModel;
	private Scanner scanner = new Scanner(System.in);

	public InterviewSetupView() {
		this.interviewSetupModel = new InterviewSetupModel(this);
	}

	public void init() {
		setupInterview();
	}

	private void setupInterview() {

		Interview interview = InterviewPanalDataBase.getInterviewInstance();
		System.out.print("Enter job position : ");
		String position = scanner.next();
		System.out.print("Enter job id : ");
		long id = scanner.nextLong();
		System.out.println("Enter hr name : ");
		String hrName = scanner.next();
		System.out.println("Enter hr email id : ");
		String hrEmailId = scanner.next();
		interview.setCompanyName("ABC Corp");
		interview.setId(id);
		interview.setPosition(position);
		interview.setHrName(hrName);
		interview.setHrEmailId(hrEmailId);
		interview.setDate(LocalDate.now().toString());
		interviewSetupModel.validate(interview);
	}

	public void onSuccess() {
		System.out.println("\n............................... Interview Setup completed :)");
		new ManageCandidateView().init();
	}

	public void showAlert(String alertText) {
		System.err.println("\n" + alertText);
		setupInterview();
	}
}
