package com.sakthi.interviewpanel;

import com.sakthi.interviewpanel.login.LoginView;

public class InterviewPanel {

	private static InterviewPanel interviewPanel;

	private final String appName = "Interview Panal System";

	private final String appVersion = "0.0.1";

	private InterviewPanel() {

	}

	public static InterviewPanel getInstance() {
		if (interviewPanel == null) {
			interviewPanel = new InterviewPanel();
		}
		return interviewPanel;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void create() {
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public static void main(String[] args) {
		InterviewPanel.getInstance().create();
	}
}
