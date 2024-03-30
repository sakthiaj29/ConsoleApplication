package com.sakthi.librarymanagement.manageuser;

import java.util.List;

import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.model.User;

public class ManageUserModel {

	private ManageUserView manageUserView;

	public ManageUserModel(ManageUserView manageUserView) {
		this.manageUserView = manageUserView;
	}

	public List<User> searchUsers(String userName) {
		return LibraryDataBase.getInstance().searchUser(userName);
	}

	public List<User> viewAllUsers() {
		return LibraryDataBase.getInstance().getAllUsers();
	}

	public void addNewUser(User user) {
		if(isValidEmail(user.getEmailId())) {
			if (LibraryDataBase.getInstance().insertUser(user)) {
				manageUserView.onUserAdded(user);
			} else {
				manageUserView.onUserExist(user);
			}
		}
		else {
			manageUserView.showAlert("Invalid email");
		}
		
	}

	private boolean isValidEmail(String emailId) {
		int AT_Count = 0, AT_Index = emailId.indexOf("@");
		int domainIndex = 0;
		String[] domainNames = { ".org", ".in", ".com" };
		for (int i = 0; i < emailId.length(); i++) {
			if ("@".equals(emailId.substring(i, i + 1))) {
				AT_Count++;
			}
		}
		for (int i = 0; i < domainNames.length; i++) {
			if (emailId.endsWith(domainNames[i])) {
				domainIndex = emailId.lastIndexOf(domainNames[i]);
			}
		}
		if (AT_Index > 0 && AT_Index + 1 < domainIndex && domainIndex != 0 && AT_Count == 1) {
			return true;
		}
		return false;
	}

}
