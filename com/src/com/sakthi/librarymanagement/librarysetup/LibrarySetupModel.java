package com.sakthi.librarymanagement.librarysetup;

import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.model.Library;

public class LibrarySetupModel {

	private LibrarySetupView librarySetupView;
	private Library library;

	LibrarySetupModel(LibrarySetupView librarySetupView) {
		this.librarySetupView = librarySetupView;
		library = LibraryDataBase.getInstance().getLibrary();
	}

	public void startSetup() {
		if (library == null || library.getId() == 0) {
			librarySetupView.initiateSetup();
		} else {
			librarySetupView.onSetupComplete("Library setup already completed");
		}
	}

	public void createLibrary(Library library) {

		if (library.getName().length() <= 3 || 50 < library.getName().length()) {
			librarySetupView.showAlert("Enter valid name");
		} else if (!isValidMail(library.getEmailId())) {
			librarySetupView.showAlert("Enter valid email");
		} else {
			this.library = LibraryDataBase.getInstance().insertLibrary(library);
			librarySetupView.onSetupComplete("Library setup complete");
		}
	}

	private boolean isValidMail(String emailId) {
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
