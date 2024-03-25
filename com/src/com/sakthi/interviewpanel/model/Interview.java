package com.sakthi.interviewpanel.model;

public class Interview {

	private String companyName;
	private String position;
	private String hrName;
	private String hrEmailId;
	private long id;
	private String date;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHrEmailId() {
		return hrEmailId;
	}

	public void setHrEmailId(String hrEmailId) {
		this.hrEmailId = hrEmailId;
	}

}
