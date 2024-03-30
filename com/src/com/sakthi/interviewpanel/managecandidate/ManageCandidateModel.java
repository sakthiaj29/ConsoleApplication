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

	public List<Candidate> getSelectedCandidate(int numberOfCandidate) {
		return InterviewPanalDataBase.getInstance()
				.getSelectedCandidateList(numberOfCandidate);
	}

	public List<Candidate> getAllCandidate() {
		return InterviewPanalDataBase.getInstance().getCandidateList();
	}

	public boolean updateCandidate(short id, byte mark) {
		return InterviewPanalDataBase.getInstance().updateCandidate(id, mark);
	}

}
