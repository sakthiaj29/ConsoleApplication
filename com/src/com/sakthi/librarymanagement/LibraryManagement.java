package com.sakthi.librarymanagement;

import com.sakthi.librarymanagement.login.LoginView;

public class LibraryManagement {

	private static LibraryManagement libraryManagement;

	private final String appName = "Library Management System";

	private final String appVersion = "0.0.2";

	private LibraryManagement() {

	}

	private void create() {
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static LibraryManagement getInstance() {
		if (libraryManagement == null)
			libraryManagement = new LibraryManagement();
		return libraryManagement;
	}

	public static void main(String[] args) {
		LibraryManagement.getInstance().create();
	}

}
