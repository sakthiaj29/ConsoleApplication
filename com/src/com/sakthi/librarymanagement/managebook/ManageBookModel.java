package com.sakthi.librarymanagement.managebook;

import java.util.List;

import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.model.Book;
import com.sakthi.librarymanagement.model.BorrowedBook;

public class ManageBookModel {

	private ManageBookView manageBookView;

	public ManageBookModel(ManageBookView manageBookView) {
		this.manageBookView = manageBookView;
	}

	public List<Book> searchBooks(String bookName) {
		return LibraryDataBase.getInstance().searchBook(bookName);
	}

	public List<Book> viewAllBooks() {
		return LibraryDataBase.getInstance().getAllBooks();
	}

	public void addNewBook(Book book) {
		if (LibraryDataBase.getInstance().insertBook(book)) {
			manageBookView.onBookAdded(book);
		} else {
			manageBookView.onBookExist(book);
		}
	}

	public Book borrowBook(long id, String emailId) {
		return LibraryDataBase.getInstance().getBook(id, emailId);
	}

	public Book returnBook(long id, String emailId) {
		return LibraryDataBase.getInstance().setBook(id, emailId);
	}

	public List<BorrowedBook> onShowBorrowedBooks(String emailId) {
		return LibraryDataBase.getInstance().getAllBorrowedBooks(emailId);
	}

}
