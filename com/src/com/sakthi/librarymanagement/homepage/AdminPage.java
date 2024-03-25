package com.sakthi.librarymanagement.homepage;

import java.util.Scanner;

import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.managebook.ManageBookView;
import com.sakthi.librarymanagement.manageuser.ManageUserView;

public class AdminPage {

	private final byte MANAGE_BOOK = 1;
	private final byte MANAGE_USER = 2;
	private final byte LOGOUT_PAGE = 0;
	private Scanner scanner = new Scanner(System.in);

	public void init() {
		System.out.println("\n Current Library : " + LibraryDataBase.getInstance().getLibrary().getName());
		onAdminPageManage();
	}

	private void onAdminPageManage() {
		byte choice = -1;
		while (true) {
			System.out.println("\n\n <-------------- ----------> Admin Page <------------------------>");
			System.out.println("\n 1)Manage Book\n\n 2)Manage User\n\n 0)Logout");
			System.out.print("\n Enter your option: ");
			choice = scanner.nextByte();
			if (choice == MANAGE_BOOK) {
				onManageBook();
			} else if (choice == MANAGE_USER) {
				onManageUser();
			} else if (choice == LOGOUT_PAGE) {
				onLogout();
			} else {
				System.err.println("\nInvalid choice, Please enter valid choice.");
			}
		}
	}

	private void onLogout() {
		new HomePageView().init();
	}

	private void onManageBook() {
		new ManageBookView().managingBooks();
	}

	private void onManageUser() {
		new ManageUserView().managingUsers();
	}
}
