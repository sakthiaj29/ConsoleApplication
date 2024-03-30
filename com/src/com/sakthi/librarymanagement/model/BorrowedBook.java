package com.sakthi.librarymanagement.model;

public class BorrowedBook {

	private Book book;
	private String emailId;
	private String borrowedDate;
	private String returnDate;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(String borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

}
