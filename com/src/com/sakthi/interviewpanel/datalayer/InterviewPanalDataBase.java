package com.sakthi.interviewpanel.datalayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sakthi.interviewpanel.model.Candidate;
import com.sakthi.interviewpanel.model.Interview;

public class InterviewPanalDataBase {

	private static InterviewPanalDataBase interviewPanalDataBase;
	private static Interview interview;
	private short candidatePrimaryId = 1;
	private List<Candidate> candidateList = new ArrayList<>();

	public static InterviewPanalDataBase getInstance() {
		if (interviewPanalDataBase == null) {
			interviewPanalDataBase = new InterviewPanalDataBase();
		}
		return interviewPanalDataBase;
	}

	public static Interview getInterviewInstance() {
		if (interview == null) {
			interview = new Interview();
		}
		return interview;
	}
	
	public int getNumberOfCandidate() {
		return candidateList.size();
	}
	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}
	
	public List<Candidate> getSelectedCandidateList(int numberOfCandidate) {
		return selectedCandidateList(candidateList,numberOfCandidate);
	}
	private List<Candidate> selectedCandidateList(List<Candidate> candidateList, int numberOfCandidate) {
		List<Candidate> selectedList=new ArrayList<>();
	
		Collections.sort(candidateList, new Comparator<Candidate>() {
      
            public int compare(Candidate o1, Candidate o2) {
                return Integer.compare(o2.getMark(), o1.getMark());
            }
        });
		if(numberOfCandidate>0) {
			for(int i=0;i<candidateList.size();i++) {
				selectedList.add(candidateList.get(i));
			}
		}
		return selectedList;
	}
	public boolean addCandidate(Candidate candidate) {
		for (Candidate candidates : candidateList) {
			if (candidate.getEmailId().equals(candidates.getEmailId())) {
				return false;
			}
		}
		candidate.setId(candidatePrimaryId++);
		candidate.setInterviewStatus("In progress");
		candidateList.add(candidate);
		return true;
	}

	public Candidate removeCandidate(String name) {
		for (Candidate candidate : candidateList) {
			if (name.equals(candidate.getName())) {
				candidateList.remove(candidate);
				return candidate;
			}
		}
		return null;
	}

	public boolean updateCandidate(short id, byte mark) {
		for (Candidate candidate : candidateList) {
			if (id==candidate.getId()) {
				candidate.setMark(mark);
				candidate.setInterviewStatus("Completed");
				return true;
			}
		}
		return false;
	}

}
