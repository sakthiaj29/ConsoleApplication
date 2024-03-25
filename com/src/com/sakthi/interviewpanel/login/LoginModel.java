package com.sakthi.interviewpanel.login;

import com.sakthi.interviewpanel.model.Credentials;

public class LoginModel {

	private LoginView loginView;
	public LoginModel(LoginView loginView) {
		this.loginView=loginView;
	}
	public void validUser(Credentials credentials) {
		if(isValidUserName(credentials.getUserName())) {
			if(isValidUserPassword(credentials.getPassword())) {
				loginView.onSuccess();
			}
			else {
				loginView.onLoginFaild("Invalid password");
			}
		}
		else {
			loginView.onLoginFaild("Invalid password");
		}
	}
	private boolean isValidUserName(String userName) {
		return userName.equals("s");
	}
	
	private boolean isValidUserPassword(String userName) {
		return userName.equals("s");
	}
	
	
}
