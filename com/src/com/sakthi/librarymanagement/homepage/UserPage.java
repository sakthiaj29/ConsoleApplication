package com.sakthi.librarymanagement.homepage;

import java.util.Scanner;

import com.sakthi.librarymanagement.datalayer.LibraryDataBase;
import com.sakthi.librarymanagement.managebook.ManageBookView;
import com.sakthi.librarymanagement.model.Book;

public class UserPage {

	private final byte VIEW_All_BOOK = 1;
	private final byte SEARCH_BOOKS = 2;
	private final byte BOOK_BORROW = 3;
	private final byte RETURN_BOOK = 4;
	private final byte BORROWED_BOOKS = 5;
	private final byte LOGOUT_PAGE = 0;
	private ManageBookView manageBookView;
	private Scanner scanner = new Scanner(System.in);

	public void init(String emailId) {
		System.out.println("\n Current Library : " + LibraryDataBase.getInstance().getLibrary().getName());
		onUserPageManage(emailId);
	}

	private void onUserPageManage(String emailId) {
		int choice = -1;
		while (true) {
			System.out.println("\n\n <------------------------> User Page <------------------------>");
			System.out.println(
					"\n 1)View All Book\n\n 2)Search Book\n\n 3)Book Borrow\n\n 4)Return Book\n\n 5)Borrowed Book\n\n 0)Logout");
			System.out.print("\n Enter your option: ");
			choice = scanner.nextInt();
			if (choice == VIEW_All_BOOK) {
				onShowAllBook();
			} else if (choice == SEARCH_BOOKS) {
				onSearchBook();
			} else if (choice == BOOK_BORROW) {
				onBookBorrow(emailId);
			} else if (choice == RETURN_BOOK) {
				onReturnBook(emailId);
			} else if (choice == BORROWED_BOOKS) {
				onShowBorrowedBooks(emailId);
			} else if (choice == LOGOUT_PAGE) {
				onLogout();
			} else {
				System.err.println("\nInvalid choice, Please enter valid choice.");
			}
		}
	}

	private void onShowBorrowedBooks(String emailId) {
		manageBookView = new ManageBookView();
		manageBookView.onShowBorrodBooks(emailId);
	}

	private void onShowAllBook() {
		manageBookView = new ManageBookView();
		manageBookView.onShowAllBooks();
	}

	private void onReturnBook(String emailId) {
		manageBookView = new ManageBookView();
		System.out.println("\n\t\t__________Return book__________");
		System.out.print("\n Enter book id : ");
		long id = scanner.nextLong();
		Book book = manageBookView.onReturnBook(id, emailId);
		if (book == null) {
			System.out.println("\n................................... Book not fount\n");
		} else {
			System.out.println("\n................................... '" + book.getName() + "' return successfully\n");
		}
	}

	private void onBookBorrow(String emailId) {
		manageBookView = new ManageBookView();
		System.out.println("\n\t\t__________Book borrow__________");
		System.out.print("\n Enter book id : ");
		long id = scanner.nextLong();
		Book book = manageBookView.onBorrowBook(id, emailId);
		if (book == null) {
			System.out.println("\n................................... Book not fount\n");
		} else {
			System.out.println("\n................................... '" + book.getName() + "' borrowed successfully\n");
		}
	}

	private void onSearchBook() {
		manageBookView = new ManageBookView();
		manageBookView.onSearchBooks();
	}

	private void onLogout() {
		new HomePageView().init();
	}
}
