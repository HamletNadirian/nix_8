package ua.com.alevel.view;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookController {
	Logger loggerInfo = LoggerFactory.getLogger("info");
	Logger loggerWarn = LoggerFactory.getLogger("warn");
	Logger loggerError = LoggerFactory.getLogger("error");
	private final BookService bookService = ObjectFactory.getCurrentObject(BookService.class);

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Select your option");
		String position;
		try {
			runNavigation();
			while ((position = reader.readLine()) != null) {
				crud(position, reader);
				position = reader.readLine();
				if (position.equals("0")) {
					System.exit(0);
				}
				crud(position, reader);
			}
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void runNavigation() {
		System.out.println();
		System.out.println("if you want add book, please enter 1");
		System.out.println("if you want update book, please enter 2");
		System.out.println("if you want delete book, please enter 3");
		System.out.println("if you want findById book, please enter 4");
		System.out.println("if you want findAll books, please enter 5");
		System.out.println("if you want find find Author ByBook, please enter 6");
		System.out.println("if you want back to the menu, please enter 7");
		System.out.println("if you want exit, please enter 0");
		System.out.println();
	}

	private void crud(String position, BufferedReader reader) throws IOException {
		switch (position) {
			case "1":
				create(reader);
				break;
			case "2":
				update(reader);
				break;
			case "3":
				delete(reader);
				break;
			case "4":
				findById(reader);
				break;
			case "5":
				findAll(reader);
				break;
			case "6":
				findAuthorByBook(reader);
				break;
			case "7":
				new Controller().run();
				break;
			case "0":
				System.exit(0);
				break;
			default:
				System.out.println("Select correct choice!");
				break;

		}
		runNavigation();
	}


	private void update(BufferedReader reader) {
		loggerInfo.info("BookController.update");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			System.out.println("Please, enter name book");
			String name = reader.readLine();
			System.out.println("Please, enter price book");
			String priceString = reader.readLine();
			int price = Integer.parseInt(priceString);
			Book book = new Book();
			book.setId(id);
			book.setPrice(price);
			book.setName(name);
			bookService.update(book);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void delete(BufferedReader reader) {
		loggerInfo.info("BookController.delete");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			bookService.delete(id);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void findById(BufferedReader reader) {
		loggerInfo.info("BookController.findById");
		try {
			System.out.println("Please, enter book id");
			String id = reader.readLine();
			Book book = new Book();
			if (book == bookService.findById(id)) {
				book = bookService.findById(id);
				System.out.println("Book = " + book);
			} else {
				System.out.println("Book with your id not found");
				loggerWarn.warn("Book with your id not found");
			}
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void findAll(BufferedReader reader) {
		loggerInfo.info("BookController.findAll");
		ArrayListMy<Book> books = bookService.findAll();
		if (books != null && books.size() != 0) {
			for (int i = 0; i < books.size(); i++) {
				System.out.println("book =" + books.get(i));
			}
		} else {
			System.out.println("users empty");
			loggerWarn.warn("users empty");
		}
	}

	private void findAuthorByBook(BufferedReader bufferedReader) {
		loggerInfo.info("BookController.findAll");
		String name = null;
		try {
			System.out.println("Please, enter book id");
			name = bufferedReader.readLine();
			if (bookService.findAuthor(name) != null) {
				System.out.println("Author name: " + bookService.findAuthor(name));
			} else {
				System.out.println("No author with name");
				loggerWarn.warn("No author with name");
			}
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void create(BufferedReader reader) {
		loggerInfo.info("AuthorController.create");
		try {

			System.out.println("Please, enter name book");
			String name = reader.readLine();
			System.out.println("Please, enter price book");
			String priceString = reader.readLine();
			int price = Integer.parseInt(priceString);
			System.out.println("Please, enter author book");
			String nameAuthor = reader.readLine();

			Book book = new Book();
			Author author = AuthorController.authorService.findAuthor(nameAuthor);

			if (author == null) {
				System.out.println("Author doesn't exist");
				loggerWarn.warn("Author doesn't exist");
			}
			book.setAuthor(author);
			book.setPrice(price);
			book.setName(name);
			bookService.create(book);
			AuthorController.authorService.findAuthor(nameAuthor).setBooks(book);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}
}
