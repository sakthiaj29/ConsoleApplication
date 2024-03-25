package com.sakthi.librarymanagement.homepage;

import java.util.Scanner;

import com.sakthi.librarymanagement.login.LoginView;
import com.sakthi.librarymanagement.datalayer.LibraryDataBase;

public class HomePageView {

	private final byte ADMIN_LOGIN = 1;
	private final byte USER_LOGIN = 2;
	private final byte EXIT = 0;
	private Scanner scanner = new Scanner(System.in);

	public void init() {
		System.out.println("\n Current Library : " + LibraryDataBase.getInstance().getLibrary().getName());
		onHomePageManage();
	}

	private void onHomePageManage() {
		byte choice = -1;
		while (true) {
			System.out.println("\n\n <------------------------> Home Page <------------------------>");
			System.out.println("\n 1)Admin login\n\n 2)User login\n\n 0)Exit");
			System.out.print("\n Enter your option: ");
			choice = scanner.nextByte();
			if (choice == ADMIN_LOGIN) {
				onAdminPage();
			} else if (choice == USER_LOGIN) {
				onUserPage();
			} else if (choice == EXIT) {
				System.out.println("\n ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ Thank you for using "
						+ LibraryDataBase.getInstance().getLibrary().getName() + " ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
				System.exit(0);
			} else {
				System.err.println("\nInvalid choice, Please enter valid choice.");
			}
		}
	}

	private void onUserPage() {
		new LoginView().initStart();
	}

	private void onAdminPage() {
		new LoginView().initStart();
	}

}
