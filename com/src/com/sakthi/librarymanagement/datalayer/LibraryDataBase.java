package com.sakthi.librarymanagement.datalayer;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import com.sakthi.librarymanagement.model.Book;
import com.sakthi.librarymanagement.model.BorrowedBook;
import com.sakthi.librarymanagement.model.Credentials;
import com.sakthi.librarymanagement.model.Library;
import com.sakthi.librarymanagement.model.User;

public class LibraryDataBase {

	private static LibraryDataBase libraryDataBase;
	private static int bookPrimaryId = 1;
	private static int libraryId = 1;
	private Library library;
	private BorrowedBook borrowedBook;
	private List<Book> bookList = new ArrayList<>();
	private List<User> userList = new ArrayList<>();
	private List<BorrowedBook> borrowedBookList = new ArrayList<>();

	public static LibraryDataBase getInstance() {
		if (libraryDataBase == null) {
			libraryDataBase = new LibraryDataBase();
		}
		return libraryDataBase;
	}

	public Library getLibrary() {
		return library;
	}

	public User getUser(String name) {
		for (User user : userList) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public Library insertLibrary(Library library) {
		library.setId(libraryId++);
		this.library = library;
		return library;
	}

	public boolean insertUser(User user) {
		boolean hasUser = false;
		for (User addedUser : userList) {
			if (addedUser.getEmailId().equals(user.getEmailId())) {
				hasUser = true;
				break;
			}
		}
		if (hasUser) {
			return false;
		} else {
			userList.add(user);
			return true;
		}
	}

	public List<User> searchUser(String userStartName) {
		List<User> searchResult = new ArrayList<>();
		for (User user : userList) {
			if (user.getName().toUpperCase().contains(userStartName.toUpperCase())) {
				searchResult.add(user);
			}
		}
		return searchResult;
	}

	public List<User> getAllUsers() {
		return userList;
	}

	public boolean isValidUserName(Credentials credentials) {
		for (User user : userList) {
			if (user.getName().equals(credentials.getUserName().trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidUserPassword(Credentials credentials) {
		for (User user : userList) {
			if (user.getName().equals(credentials.getUserName().trim())) {
				if (user.getPassword().equals(credentials.getPassword().trim())) {
					return true;
				}
			}
		}
		return false;
	}

	public Book getBook(long id, String emailId) {
		if (!isExistBookAndEmail(id, emailId)) {
			for (Book book : bookList) {
				if (book.getId() == id && book.getCount() > 0) {
					int count = book.getCount();
					addBorrowedBook(book, emailId);
					book.setCount(--count);
					return book;
				}
			}
		}
		return null;
	}

	private boolean isExistBookAndEmail(long id, String emailId) {
		for (BorrowedBook borrowedBook : borrowedBookList) {
			if (emailId.equals(borrowedBook.getEmailId()) && id == borrowedBook.getBook().getId()) {
				return true;
			}
		}
		return false;
	}

	private void addBorrowedBook(Book book, String email) {
		borrowedBook = new BorrowedBook();
		borrowedBook.setBook(book);
		borrowedBook.setEmailId(email);
		borrowedBook.setBorrowedDate(LocalDate.now());
		borrowedBook.setReturnDate(LocalDate.now().plusDays(30));
		borrowedBookList.add(borrowedBook);
	}

	public Book setBook(long id, String emailId) {
		if (borrowedBook.getEmailId().equals(emailId) && borrowedBook.getBook().getId() == id) {
			int count = borrowedBook.getBook().getCount();
			borrowedBook.getBook().setCount(++count);
			borrowedBookList.remove(borrowedBook);
			return borrowedBook.getBook();
		}
		return null;
	}

	public boolean insertBook(Book book) {
		boolean hasBook = false;
		for (Book addedBook : bookList) {
			if (addedBook.getName().equals(book.getName()) && addedBook.getAuthor().equals(book.getAuthor())) {
				hasBook = true;
				break;
			}
		}
		if (hasBook) {
			return false;
		} else {
			book.setId(bookPrimaryId++);
			bookList.add(book);
			return true;
		}

	}

	public List<Book> searchBook(String bookStartName) {
		List<Book> searchResult = new ArrayList<>();
		for (Book book : bookList) {
			if (book.getName().toUpperCase().contains(bookStartName.toUpperCase())) {
				searchResult.add(book);
			}
		}
		return searchResult;
	}

	public List<Book> getAllBooks() {
		return bookList;
	}

	public List<BorrowedBook> getBorrowedBook() {
		return borrowedBookList;
	}

	public List<BorrowedBook> getAllBorrowedBooks(String emailId) {
		List<BorrowedBook> borrowedBooks = new ArrayList<>();
		for (BorrowedBook borrowedBook : borrowedBookList) {
			if (emailId.equals(borrowedBook.getEmailId())) {
				borrowedBooks.add(borrowedBook);
			}
		}
		return borrowedBooks;
	}

}
