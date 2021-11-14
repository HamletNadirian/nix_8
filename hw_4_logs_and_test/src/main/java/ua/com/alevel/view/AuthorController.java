package ua.com.alevel.view;


import ch.qos.logback.core.status.StatusUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.entity.Author;
import ua.com.alevel.service.AuthorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AuthorController {

	Logger loggerInfo = LoggerFactory.getLogger("info");
	Logger loggerWarn = LoggerFactory.getLogger("warn");
	Logger loggerError = LoggerFactory.getLogger("error");

	static final AuthorService authorService = ObjectFactory.getCurrentObject(AuthorService.class);

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("select your option");
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
		System.out.println("if you want add author, please enter 1");
		System.out.println("if you want update author, please enter 2");
		System.out.println("if you want delete all book's author , please enter 3");
		System.out.println("if you want findById author, please enter 4");
		System.out.println("if you want findAll authors, please enter 5");
		System.out.println("if you want findAll books by author, please enter 6");
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
				findAuthorByName(reader);
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

	private void create(BufferedReader reader) {
		loggerInfo.info("Start create author");
		try {
			System.out.println("Please, enter name author");
			String name = reader.readLine();
			Author author = new Author();
			author.setName(name);

			authorService.create(author);

		} catch (IOException e) {
			System.out.println("problem: " + e.getMessage());
			loggerError.error("problem: " + e.getMessage());
		}
	}


	private void update(BufferedReader reader) {
		loggerInfo.info("Start create author");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			System.out.println("Please, enter name authors");
			String name = reader.readLine();
			Author author = new Author();
			author.setId(id);
			author.setName(name);
			authorService.update(author);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void delete(BufferedReader reader) {
		loggerInfo.info("Author Controller.delete");
		try {
			System.out.println("Please, enter name author's ");
			String name = reader.readLine();
			if (StringUtils.isNotBlank(name)) {
				authorService.delete(name);
			} else {
				throw new RuntimeException("author not found by id " + name);
			}
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void findById(BufferedReader reader) {
		loggerInfo.info("AuthorController.findById");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			Author author = authorService.findById(id);
			System.out.println("author = " + author);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

	private void findAll(BufferedReader reader) {
		loggerInfo.info("AuthorController.findAll");
		ArrayListMy<Author> authors = authorService.findAll();
		if (authors != null && authors.size() != 0) {
			for (int i = 0; i < authors.size(); i++) {
				System.out.println("author :" + authors.get(i));
			}
		} else {
			System.out.println("empty");
			loggerWarn.warn("empty");
		}
	}

	private void findAuthorByName(BufferedReader bufferedReader) {
		loggerInfo.info("AuthorController.findAll");
		String name = null;
		try {
			System.out.println("Please, enter name author");
			name = bufferedReader.readLine();
			if (authorService.findAuthor(name) != null) {
				System.out.println("Author name: " + authorService.findAuthor(name));
			} else {
				System.out.println("No author with name");
				loggerWarn.warn("No author with name");
			}
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
			loggerError.error("problem: = " + e.getMessage());
		}
	}

}
