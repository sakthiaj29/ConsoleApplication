package com.sakthi.interviewpanel.managecandidate;

import java.util.List;

import com.sakthi.interviewpanel.datalayer.InterviewPanalDataBase;
import com.sakthi.interviewpanel.model.Candidate;
import com.sakthi.librarymanagement.datalayer.LibraryDataBase;

public class ManageCandidateModel {

	private ManageCandidateView manageCandidateView;
	private List<Candidate> candidateList;
	
	public ManageCandidateModel(ManageCandidateView manageCandidateView) {
		this.manageCandidateView=manageCandidateView;
	}

	public void addCandidate(Candidate candidate) {
		InterviewPanalDataBase.getInstance().getCandidateList();
		if(InterviewPanalDataBase.getInstance().addCandidate(candidate)) {
			manageCandidateView.onSuccess(candidate);
		}else {
			manageCandidateView.onExistCandidate(candidate);
		}
	}

	public void removeCandidate(String name) {
		Candidate candidate=InterviewPanalDataBase.getInstance().removeCandidate(name);
		if(candidate==null) {
			manageCandidateView.onRemoveFaild(candidate);
		}
		else {
			manageCandidateView.onRemoveSuccess(candidate);
		}
	}

}
