package com.sakthi.librarymanagement.datalayer;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakthi.librarymanagement.model.Book;
import com.sakthi.librarymanagement.model.BorrowedBook;
import com.sakthi.librarymanagement.model.Credentials;
import com.sakthi.librarymanagement.model.Library;
import com.sakthi.librarymanagement.model.User;

public class LibraryDataBase {

	private static LibraryDataBase libraryDataBase;
	private String bookJsonPath = "C:\\Users\\ASUS\\Desktop\\java\\com\\src\\com\\sakthi\\librarymanagement\\JsonFiles\\bookData.json";
	private String userJsonPath = "C:\\Users\\ASUS\\Desktop\\java\\com\\src\\com\\sakthi\\librarymanagement\\JsonFiles\\userData.json";
	private String borrowedBookJsonPath = "C:\\Users\\ASUS\\Desktop\\java\\com\\src\\com\\sakthi\\librarymanagement\\JsonFiles\\borrowerBookData.json";
	private String libraryJsonPath = "C:\\Users\\ASUS\\Desktop\\java\\com\\src\\com\\sakthi\\librarymanagement\\JsonFiles\\libraryData.json";;
	private static long bookPrimaryId = 0;
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
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(libraryJsonPath);
		try {
			library = mapper.readValue(file, new TypeReference<Library>() {
			});
		} catch (Exception e) {
			e.getStackTrace();
		}
		return library;
	}

	public Library insertLibrary(Library library) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(libraryJsonPath);
		try {
			if (file.length() > 0) {
				file.createNewFile();
			}
			library.setId(1);
			this.library = library;
			mapper.writeValue(file, library);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return library;
	}

	public boolean isValidUserName(Credentials credentials) {
		List<User> userList = getAllUsers();
		for (User user : userList) {
			if (user.getName().equals(credentials.getUserName().trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidUserPassword(Credentials credentials) {
		List<User> userList = getAllUsers();
		for (User user : userList) {
			if (user.getName().equals(credentials.getUserName().trim())) {
				if (user.getPassword().equals(credentials.getPassword().trim())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isExistBorrowedBookList(long id, String emailId) {
		borrowedBookList = getBorrowedBook();
		for (BorrowedBook borrowedBook : borrowedBookList) {
			if (emailId.equals(borrowedBook.getEmailId()) && id == borrowedBook.getBook().getId()) {
				return true;
			}
		}
		return false;
	}

	public Book setBorrowedBook(long id, String emailId) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(bookJsonPath);
		File file1=new File(borrowedBookJsonPath);
		Book book = new Book();
		borrowedBook=new BorrowedBook();
		try {
			if(!file1.exists()) {
				file1.createNewFile();
			}
			borrowedBookList = getBorrowedBook();
			bookList = getAllBooks();
			for (int i=0;i<borrowedBookList.size();i++) {
				if (borrowedBookList.get(i).getEmailId().equals(emailId) && borrowedBookList.get(i).getBook().getId() == id) {
					book = borrowedBookList.get(i).getBook();
					borrowedBookList.remove(i);
				}
			}
			for(int i=0;i<bookList.size();i++) {
				if(bookList.get(i).getId()==id) {
					bookList.remove(i);
				}
			}
			if(book.getId()!=0) {
				int count = book.getCount();
				book.setCount(++count);
				bookList.add(book);
				mapper.writeValue(file1, borrowedBookList);
				mapper.writeValue(file, bookList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return book;
	}

	private void addBorrowedBook(Book book, String email) {
		borrowedBookList = getBorrowedBook();
		borrowedBook = new BorrowedBook();
		borrowedBook.setBook(book);
		borrowedBook.setEmailId(email);
		borrowedBook.setBorrowedDate(LocalDate.now().toString());
		borrowedBook.setReturnDate(LocalDate.now().plusDays(30).toString());
		borrowedBookList.add(borrowedBook);

		ObjectMapper mapper = new ObjectMapper();
		File file = new File(borrowedBookJsonPath);
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			mapper.writeValue(file, borrowedBookList);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return;
	}

	public List<BorrowedBook> getBorrowedBook() {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(borrowedBookJsonPath);
		List<BorrowedBook> borrowedBookList = new ArrayList<>();
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				borrowedBookList = mapper.readValue(file, new TypeReference<List<BorrowedBook>>() {
				});
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return borrowedBookList;
	}

	public List<BorrowedBook> getAllBorrowedBooks(String emailId) {
		
		List<BorrowedBook> borrowedBooks = new ArrayList<>();
		
		try {
			borrowedBookList = getBorrowedBook();
			for (BorrowedBook borrowedBook : borrowedBookList) {
				if (emailId.equals(borrowedBook.getEmailId())) {
					borrowedBooks.add(borrowedBook);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return borrowedBooks;
	}

//Book
	public List<Book> searchBook(String bookStartName) {
		List<Book> searchResult = new ArrayList<>();
		try {
			bookList = getAllBooks();
			for (Book book : bookList) {
				if (book.getName().toUpperCase().contains(bookStartName.toUpperCase())) {
					searchResult.add(book);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return searchResult;
	}

	public List<Book> getAllBooks() {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(bookJsonPath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				bookList = mapper.readValue(file, new TypeReference<List<Book>>() {
				});
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return bookList;
	}

	public Book getBorrowedBook(long id, String emailId) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(bookJsonPath);
		Book book=new Book();
		try {
			if (!isExistBorrowedBookList(id, emailId)) {
				bookList = getAllBooks();
				for (Book books : bookList) {
					if (books.getId() == id && books.getCount() > 0) {
						book=books;
					}
				}
				int count = book.getCount();
				bookList.remove(book);
				book.setCount(--count);
				bookList.add(book);
				mapper.writeValue(file, bookList);
				addBorrowedBook(book, emailId);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return book;
	}

	public boolean insertBook(Book book) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(bookJsonPath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				bookList = mapper.readValue(file, new TypeReference<List<Book>>() {
				});
				for (Book addedBook : bookList) {
					if (addedBook.getName().equals(book.getName()) && addedBook.getAuthor().equals(book.getAuthor())) {
						return false;
					}
				}
				bookPrimaryId = bookList.get(bookList.size() - 1).getId();
			}
			book.setId(++bookPrimaryId);
			bookList.add(book);
			mapper.writeValue(file, bookList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

//User
	public List<User> getAllUsers() {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(userJsonPath);
		List<User> getUsers = new ArrayList<>();
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				getUsers = mapper.readValue(file, new TypeReference<List<User>>() {
				});
				
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return getUsers;
	}

	public boolean insertUser(User user) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(userJsonPath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				userList = mapper.readValue(file, new TypeReference<List<User>>() {
				});
				for (User users : userList) {
					if (user.getEmailId().equals(users.getEmailId())) {
						return false;
					}
				}
			}
			userList.add(user);
			mapper.writeValue(file, userList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public User getUser(String name) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(userJsonPath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				userList = mapper.readValue(file, new TypeReference<List<User>>() {
				});
				for (User user : userList) {
					if (user.getName().equals(name)) {
						return user;
					}
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public List<User> searchUser(String userName) {
		List<User> searchResult = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(userJsonPath);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.length() > 0) {
				userList = mapper.readValue(file, new TypeReference<List<User>>() {});
				for (User user : userList) {
					if (user.getName().toUpperCase().contains(userName.toUpperCase())) {
						searchResult.add(user);
					}
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return searchResult;
	}
}
