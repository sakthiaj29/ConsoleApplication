package com.sakthi.librarymanagement.librarysetup;

import java.util.Scanner;

import com.sakthi.librarymanagement.homepage.AdminPage;
import com.sakthi.librarymanagement.model.Library;

public class LibrarySetupView {

	private LibrarySetupModel librarySetupModel;
	private AdminPage adminPage;
	private Scanner scanner = new Scanner(System.in);

	public LibrarySetupView() {
		librarySetupModel = new LibrarySetupModel(this);
	}

	public void init() {
		librarySetupModel.startSetup();
	}

	public void onSetupComplete(String msg) {
		System.out.println("\n\n\t***************** " + msg + " *****************\n");
		adminPage = new AdminPage();
		adminPage.init();
	}

	public void initiateSetup() {
		Library library = new Library();
		System.out.print("\n Enter library name: ");
		String name = scanner.nextLine();
		System.out.print("\n Enter library email id: ");
		String emailId = scanner.next();
		library.setName(name);
		library.setEmailId(emailId);
		librarySetupModel.createLibrary(library);
	}

	public void showAlert(String alert) {
		System.err.println("\n\t" + alert);
		initiateSetup();
	}

}
