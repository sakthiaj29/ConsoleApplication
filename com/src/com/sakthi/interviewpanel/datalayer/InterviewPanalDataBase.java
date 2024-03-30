package com.sakthi.interviewpanel.datalayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakthi.interviewpanel.model.Candidate;
import com.sakthi.interviewpanel.model.Interview;

public class InterviewPanalDataBase {

	private static InterviewPanalDataBase interviewPanalDataBase;
	private static final String candidateJsonPath = "C:\\Users\\ASUS\\Desktop\\java\\com\\src\\com\\sakthi\\interviewpanel\\jsonfiles\\candidate.json";
	private static Interview interview;
	private short candidatePrimaryId = 0;
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

	public List<Candidate> getCandidateList() {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(candidateJsonPath);
		candidateList = new ArrayList<>();
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				candidateList = mapper.readValue(file, new TypeReference<List<Candidate>>() {
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return candidateList;
	}

	public List<Candidate> getSelectedCandidateList(int numberOfCandidate) {

		List<Candidate> selectedList = new ArrayList<>();
		try {
			candidateList = getCandidateList();
			Collections.sort(candidateList, new Comparator<Candidate>() {

				public int compare(Candidate o1, Candidate o2) {
					return Integer.compare(o2.getMark(), o1.getMark());
				}
			});
			if (numberOfCandidate > 0) {
				for (int i = 0; i < candidateList.size(); i++) {
					if(candidateList.get(i).getMark()>4) {
						selectedList.add(candidateList.get(i));	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectedList;
	}

	public boolean addCandidate(Candidate candidate) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(candidateJsonPath);
		candidateList = new ArrayList<>();
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				candidateList = mapper.readValue(file, new TypeReference<List<Candidate>>() {
				});
				for (Candidate candidates : candidateList) {
					if (candidate.getEmailId().equals(candidates.getEmailId())) {
						return false;
					}
				}
				candidatePrimaryId = candidateList.get(candidateList.size() - 1).getId();
			}
			candidate.setId(++candidatePrimaryId);
			candidate.setInterviewStatus("In progress");
			candidateList.add(candidate);
			mapper.writeValue(file, candidateList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public Candidate removeCandidate(String name) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(candidateJsonPath);
		candidateList = new ArrayList<>();
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				candidateList = mapper.readValue(file, new TypeReference<List<Candidate>>() {
				});
				for (Candidate candidate : candidateList) {
					if (name.equals(candidate.getName())) {
						candidateList.remove(candidate);
						mapper.writeValue(file, candidateList);
						return candidate;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateCandidate(short id, byte mark) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(candidateJsonPath);
		candidateList = new ArrayList<>();
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				candidateList = mapper.readValue(file, new TypeReference<List<Candidate>>() {
				});
				for (Candidate candidate : candidateList) {
					if (id == candidate.getId()) {
						candidate.setMark(mark);
						candidate.setInterviewStatus("Completed");
						mapper.writeValue(file, candidateList);
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
