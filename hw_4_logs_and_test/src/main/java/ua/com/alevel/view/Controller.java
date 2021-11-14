package ua.com.alevel.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

	private void runNavigation() {
		System.out.println();
		System.out.println("if you want add author, please enter 1");
		System.out.println("if you want add book, please enter 2");
		System.out.println("if you want exit, please enter 0");
		System.out.println();
	}

	private void menu(String position, BufferedReader reader) throws IOException {
		switch (position) {
			case "1":
				new AuthorController().run();
				break;
			case "2":
				new BookController().run();
				break;
			case "0":
				System.exit(0);
				break;
			default:
				System.out.println("Select correct choice!");
		}
		runNavigation();
	}

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Select your option");
		String position;
		try {
			runNavigation();
			while ((position = reader.readLine()) != null) {
				menu(position, reader);
				position = reader.readLine();
				if (position.equals("0")) {
					System.exit(0);
				}
				menu(position, reader);
			}
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}
}
