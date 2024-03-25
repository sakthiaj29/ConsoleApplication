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
		if (LibraryDataBase.getInstance().insertUser(user)) {
			manageUserView.onUserAdded(user);
		} else {
			manageUserView.onUserExist(user);
		}
	}

}
