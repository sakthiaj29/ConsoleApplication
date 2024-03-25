package com.sakthi.interviewpanel.login;

import java.util.Scanner;

import com.sakthi.interviewpanel.InterviewPanel;
import com.sakthi.interviewpanel.managecandidate.ManageCandidateView;
import com.sakthi.interviewpanel.model.Credentials;

public class LoginView {

	private LoginModel loginModel;
	private Scanner scanner=new Scanner(System.in);
	
	public LoginView() {
		loginModel=new LoginModel(this);
	}
	public void init() {
		System.out.println("\t\t --------- "+InterviewPanel.getInstance().getAppName()+" ---------"
				+"\n\t\t        ------ Version: "+InterviewPanel.getInstance().getAppVersion()+" ------");
		proceedLogin();
	}
	private void proceedLogin() {
		Credentials credentials=new Credentials();
		System.out.print("Enter user name: ");
		credentials.setUserName(scanner.next());
		System.out.print("\nEnter password: ");
		credentials.setPassword(scanner.next());
		loginModel.validUser(credentials);
	}
	public void onSuccess() {
		System.out.println("\n....................................... Login successfully :)");
		new ManageCandidateView().init();
	}
	public void onLoginFaild(String alertText) {
		System.err.println(alertText);
		checkForLogin();
	}
	private void checkForLogin() {
		System.out.println("Do you want to continue?\n Type Yes/No");
		String choice=scanner.next();
		if("Yes".equalsIgnoreCase(choice)) {
			proceedLogin();
		}
		else if("No".equalsIgnoreCase(choice)){
			System.out.println("\n\t ---------- Thank you ----------");
			
		}
		else {
			System.out.println("\nInvalid choice, Please enter valid choice.");
			checkForLogin();
		}
	}
	
}
