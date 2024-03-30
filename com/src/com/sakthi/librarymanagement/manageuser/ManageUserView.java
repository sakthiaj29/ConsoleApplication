package com.sakthi.librarymanagement.manageuser;

import java.util.List;

import java.util.Scanner;

import com.sakthi.librarymanagement.homepage.AdminPage;
import com.sakthi.librarymanagement.model.User;

public class ManageUserView {

	private final byte ADD_USER = 1;
	private final byte SEARCH_USER = 2;
	private final byte VIEW_ALL_USER = 3;
	private final byte EXIT = 0;
	private Scanner scanner = new Scanner(System.in);
	private ManageUserModel manageUserModel;

	public ManageUserView() {
		manageUserModel = new ManageUserModel(this);
	}

	public void managingUsers() {
		while (true) {
			System.out.println("\n\n <---------------------> Manage User Page <--------------------->");
			System.out.println("\n 1)Add user\n\n 2)Search users\n\n 3)View all users\n\n 0)Exit");
			System.out.print("\n Enter your option : ");
			byte option = scanner.nextByte();
			if (option == SEARCH_USER) {
				onSearchUsers();
			} else if (option == ADD_USER) {
				initAdd();
			} else if (option == VIEW_ALL_USER) {
				onViewAllUsers();
			} else if (option == EXIT) {
				new AdminPage().init();
			} else {
				System.out.println("\n\t Choice valid option");
			}
		}
	}

	public void initAdd() {
		System.out.println("\n__________Add user__________");
		User user = new User();
		System.out.print("\nEnter user name: ");
		user.setName(scanner.next());
		System.out.print("\nEnter user email id: ");
		user.setEmailId(scanner.next());
		System.out.print("\nEnter user password: ");
		user.setPassword(scanner.next());
		manageUserModel.addNewUser(user);
	}

	private void checkForAddNewUser() {
		System.out.println("\nDo you want to add more users? \nType Yes/No");
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("yes")) {
			initAdd();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding users");
			managingUsers();
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewUser();
		}
	}

	public void onViewAllUsers() {
		System.out.println("\n\t\t__________View all user__________");
		List<User> userList = manageUserModel.viewAllUsers();
		onViewUsers(userList);
	}

	public void onSearchUsers() {
		System.out.println("\n\t\t__________Search user__________");
		System.out.print("\n Enter user name : ");
		String name = scanner.next();
		List<User> userList = manageUserModel.searchUsers(name);
		onViewUsers(userList);
	}

	private void onViewUsers(List<User> userList) {
		if (userList.size() == 0) {
			System.out.println("\n ................................... No match exist :(");
		} else {
			System.out.printf("\n %-20s\t %-10s ", "User name", "User email Id");
			System.out.println("\n ----------------------------------------");
			for (User user : userList) {
				System.out.printf(" %-20s\t %-10s", user.getName(), user.getEmailId());
				System.out.println();
			}
		}
	}

	public void onUserAdded(User user) {
		System.out.println("\n................................... User '" + user.getName() + "' added successfully\n");
		checkForAddNewUser();
	}

	public void onUserExist(User user) {
		System.out.println("\n................................... User '" + user.getName() + "' already exist\n");
		checkForAddNewUser();
	}
	public void showAlert(String alertText) {
		System.err.println("\n\t\t"+alertText);
		initAdd();
	}

}
