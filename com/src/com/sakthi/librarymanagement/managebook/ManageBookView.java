package com.sakthi.librarymanagement.managebook;

import java.util.List;
import java.util.Scanner;

import com.sakthi.librarymanagement.homepage.AdminPage;
import com.sakthi.librarymanagement.model.Book;
import com.sakthi.librarymanagement.model.BorrowedBook;

public class ManageBookView {

	private final byte ADD_BOOK = 1;
	private final byte SEARCH_BOOKS = 2;
	private final byte VIEW_ALL_BOOKS = 3;
	private final byte EXIT = 0;
	private static Scanner scanner = new Scanner(System.in);
	private ManageBookModel manageBookModel;

	public ManageBookView() {
		manageBookModel = new ManageBookModel(this);
	}

	public void managingBooks() {
		while (true) {
			System.out.println("\n\n <---------------------> Manage Book Page <--------------------->");
			System.out.println("\n 1)Add book\n\n 2)Search books\n\n 3)View all books\n\n 0)Exit");
			System.out.print("\n Enter your option : ");
			byte option = scanner.nextByte();
			if (option == SEARCH_BOOKS) {
				onSearchBooks();
			} else if (option == ADD_BOOK) {
				initAdd();
			} else if (option == VIEW_ALL_BOOKS) {
				onShowAllBooks();
			} else if (option == EXIT) {
				new AdminPage().init();
			} else {
				System.out.println("\n\t Choice valid option");
			}
		}
	}

	public void initAdd() {
		System.out.println("\n\t__________ Add book __________");
		Book book = new Book();
		System.out.print("\nEnter book name: ");
		scanner.nextLine();
		book.setName(scanner.nextLine());

		System.out.print("\nEnter book author: ");
		book.setAuthor(scanner.nextLine());
		
		System.out.print("\nEnter book genre: ");
		book.setGenre(scanner.nextLine());
		
		System.out.print("\nEnter book count: ");
		book.setCount(scanner.nextInt());
		manageBookModel.addNewBook(book);
	}

	private void checkForAddNewBook() {
		System.out.println("\nDo you want to add more books? \nType Yes/No");
		String choice = scanner.next();
		if (choice.equalsIgnoreCase("yes")) {
			initAdd();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n Thanks for adding books");
			managingBooks();
		} else {
			System.out.println("\nInvalid choice, Please enter valid choice.\n");
			checkForAddNewBook();
		}
	}

	public void onBookAdded(Book book) {
		System.out.println("\n................................... Book '" + book.getName() + "' added successfully\n");
		checkForAddNewBook();
	}

	public void onBookExist(Book book) {
		System.out.println("\n................................... Book '" + book.getName() + "' already exist\n");
		checkForAddNewBook();
	}

	public void onSearchBooks() {
		System.out.println("\n\t\t__________ Search book __________");
		System.out.print("\n Enter book name : ");
		String name = scanner.next();
		List<Book> bookList = manageBookModel.searchBooks(name);
		onShowBooks(bookList);
	}

	public void onShowAllBooks() {
		List<Book> bookList = manageBookModel.viewAllBooks();
		System.out.println("\n\t\t__________ View all book __________");
		onShowBooks(bookList);
	}

	private void onShowBooks(List<Book> bookList) {
		if (bookList.size() == 0) {
			System.out.println("\n ................................... No Book exist :(");
		} else {
			String available = "";
			System.out.printf("\n %-20s\t %-8s\t %-10s\t %-10s\t %s", "Book Name", "Book Id", "Book Author",
					"Book Count", "Available/Not");
			System.out
					.println("\n ------------------------------------------------------------------------------------");
			for (Book book : bookList) {
				available = book.getCount() > 0 ? "Available" : "Not available";
				System.out.printf(" %-20s\t %-8d\t %-10s\t %-10s\t %s", book.getName(), book.getId(), book.getAuthor(),
						book.getCount(), available);
				System.out.println();
			}
		}
	}

	public Book onBorrowBook(long id, String emailId) {
		return manageBookModel.borrowBook(id, emailId);
	}

	public Book onReturnBook(long id, String emailId) {
		return manageBookModel.returnBook(id, emailId);
	}

	public void onShowBorrodBooks(String emailId) {
		List<BorrowedBook> borrowedBooks = manageBookModel.onShowBorrowedBooks(emailId);
		if (borrowedBooks.size() == 0) {
			System.out.println("\n ................................... No Book exist :(");
		} else {
			System.out.println("\n\t\t__________ Borrowed book __________");
			System.out.println("\n Email Id : " + emailId);
			System.out.printf("\n %-18s\t %-8s\t %-10s\t %-12s\t %s", "Book Name", "Book Id", "Book Author",
					"Borrwed Date", "Return Date");
			System.out.println("\n ------------------------------------------------------------------------------------");
			for (BorrowedBook borrowedBook : borrowedBooks) {
				System.out.printf(" %-18s\t %-8s\t %-10s\t %-12s\t %s", borrowedBook.getBook().getName(),
						borrowedBook.getBook().getId(), borrowedBook.getBook().getAuthor(),
						borrowedBook.getBorrowedDate(), borrowedBook.getReturnDate());
				System.out.println();
			}
		}
	}

}
