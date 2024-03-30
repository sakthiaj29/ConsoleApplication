package com.sakthi.librarymanagement.login;

import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.model.Credentials;

public class LoginModel {

	private LoginView loginView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	public void validateUser(Credentials credentials) {
		if (isValidateAdminName(credentials.getUserName().trim())) {
			if (isValidateAdminPassword(credentials.getPassword().trim())) {
				loginView.onSuccessAdmin();
			} else {
				loginView.onLoginFailed("Invalid password");
			}
		} else if (isValidateUserName(credentials)) {
			if (isValidatePassword(credentials)) {
				loginView.onSuccessUser(LibraryDataBase.getInstance().getUser(credentials.getUserName()).getEmailId());
			} else {
				loginView.onLoginFailed("Invalid password");
			}
		} else {
			loginView.onLoginFailed("Invalid user name");
		}
	}

	private boolean isValidatePassword(Credentials credentials) {
		return LibraryDataBase.getInstance().isValidUserPassword(credentials);
	}

	private boolean isValidateUserName(Credentials credentials) {
		return LibraryDataBase.getInstance().isValidUserName(credentials);
	}

	private boolean isValidateAdminPassword(String password) {
		return password.equals("sakthi123");
	}

	private boolean isValidateAdminName(String userName) {
		return userName.equals("adminSakthi");
	}
}
