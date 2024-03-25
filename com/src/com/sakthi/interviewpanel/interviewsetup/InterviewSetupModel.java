package com.sakthi.interviewpanel.interviewsetup;

import com.sakthi.interviewpanel.model.Interview;

public class InterviewSetupModel {

	private InterviewSetupView interviewSetupView;

	public InterviewSetupModel(InterviewSetupView interviewSetupView) {
		this.interviewSetupView = interviewSetupView;
	}

	public void validate(Interview interview) {
		if (isValidEmail(interview.getHrEmailId())) {
			interviewSetupView.onSuccess();
		} else {
			interviewSetupView.showAlert("Invalid email id");
		}
	}

	private boolean isValidEmail(String hrEmailId) {
		int AT_Count = 0, AT_Index = hrEmailId.indexOf("@");
		int domainIndex = 0;
		String[] domainNames = { ".org", ".in", ".com" };
		for (int i = 0; i < hrEmailId.length(); i++) {
			if ("@".equals(hrEmailId.substring(i, i + 1))) {
				AT_Count++;
			}
		}
		for (int i = 0; i < domainNames.length; i++) {
			if (hrEmailId.endsWith(domainNames[i])) {
				domainIndex = hrEmailId.lastIndexOf(domainNames[i]);
			}
		}
		if (AT_Index > 0 && AT_Index + 1 < domainIndex && domainIndex != 0 && AT_Count == 1) {
			return true;
		}
		return false;
	}
}
