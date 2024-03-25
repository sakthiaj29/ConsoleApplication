package com.sakthi.librarymanagement.login;

import com.sakthi.librarymanagement.model.Credentials;

import java.util.Scanner;

import com.sakthi.librarymanagement.LibraryManagement;
import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.homepage.AdminPage;
import com.sakthi.librarymanagement.homepage.UserPage;
import com.sakthi.librarymanagement.librarysetup.LibrarySetupView;

public class LoginView {

	private LoginModel loginModel;
	private AdminPage adminPage;
	private UserPage userPage;
	private LibrarySetupView librarySetupView;
	private Scanner scanner = new Scanner(System.in);

	public LoginView() {
		loginModel = new LoginModel(this);
	}

	public void init() {
		System.out.println("\t\t--- " + LibraryManagement.getInstance().getAppName() + " ---\n" + "\t\t\t--- version: "
				+ LibraryManagement.getInstance().getAppVersion() + " ---\n");
		System.out.println("Please login to proceed.");
		proceedLogin();
	}

	public void initStart() {
		proceedLogin();
	}

	public void onSuccessAdmin() {
		System.out.println("\n ----------------------------------------------- Login successful :)");
		if (LibraryDataBase.getInstance().getLibrary() == null) {
			librarySetupView = new LibrarySetupView();
			librarySetupView.init();
		} else {
			adminPage = new AdminPage();
			adminPage.init();
		}
	}

	public void onSuccessUser(String emailId) {
		userPage = new UserPage();
		userPage.init(emailId);
	}

	public void onLoginFailed(String alertText) {
		System.err.println("\n\t" + alertText);
		checkForLogin();
	}

	private void checkForLogin() {
		System.out.println("\nDo you try again? \nType Yes/No");
		String choice = scanner.next();
		if ("Yes".equalsIgnoreCase(choice)) {
			proceedLogin();
		} else if ("No".equalsIgnoreCase(choice)) {
			System.out.println("\n\t\t ---- Thanks You ----");
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.");
			checkForLogin();
		}
	}

	private void proceedLogin() {
		Credentials credential = new Credentials();
		System.out.print("\n Enter name: ");
		credential.setUserName(scanner.nextLine());
		System.out.print("\n Enter password: ");
		credential.setPassword(scanner.nextLine());
		loginModel.validateUser(credential);
	}
}
