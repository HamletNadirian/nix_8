package ua.com.alevel.view;

import ua.com.alevel.service.CalendarService;
import ua.com.alevel.entity.MyCalendar;
import static ua.com.alevel.entity.MyCalendar.getMonthName;
import static ua.com.alevel.util.Calculation.*;
import static ua.com.alevel.util.UtilDateConverter.*;
import static ua.com.alevel.util.UtilDateFormat.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CalendarController {

	private final CalendarService calendarService = new CalendarService();

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Select your option");
		String position;
		try {
			runNavigation();
			while ((position = reader.readLine()) != null) {
				crud(position, reader);
				if (position.equals("0")) {
					System.exit(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void runNavigation() {
		System.out.println();
		System.out.println("if you want create date, please enter 1");
		System.out.println("if you want update date, please enter 2");
		System.out.println("if you want delete date, please enter 3");
		System.out.println("if you want findById date, please enter 4");
		System.out.println("if you want findAll date, please enter 5");
		System.out.println("if you want difference between two dates by id, please enter 6");
		System.out.println("if you want add date to date, please enter 7");
		System.out.println("if you want sub date to date, please enter 8");
		System.out.println("if you want sortAsc date, please enter 9");
		System.out.println("if you want sortDesc date, please enter 10");
		System.out.println("if you want output date, please enter 11");
		System.out.println("if you want exit, please enter, please enter 0");
		System.out.println();
	}

	private void crud(String position, BufferedReader reader) throws IOException {
		switch (position) {
			case "1" -> create(reader);
			case "2" -> update(reader);
			case "3" -> delete(reader);
			case "4" -> findById(reader);
			case "5" -> findAll(reader);
			case "6" -> diffById(reader);
			case "7" -> addById(reader);
			case "8" -> subById(reader);
			case "9" -> sortAsc(reader);
			case "10" -> sortDesc(reader);
			case "11" -> formatOutput(reader);
		}
		runNavigation();
	}

	private void create(BufferedReader reader) throws IOException {
		System.out.println("CalendarController.create");
		MyCalendar myCalendar = new MyCalendar();
		myCalendar = input(myCalendar);
		calendarService.create(myCalendar);
		System.out.println();
	}

	private void update(BufferedReader reader) {
		System.out.println("UserController.update");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			MyCalendar myCalendar = new MyCalendar();
			//String date = reader.readLine();
			System.out.println("Please, enter date ");
			myCalendar = input(myCalendar);
			myCalendar.setId(id);
			calendarService.update(myCalendar);
		} catch (IOException | NullPointerException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}

	private void delete(BufferedReader reader) {
		System.out.println("BookController.delete");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			calendarService.delete(id);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}

	private void findById(BufferedReader reader) {
		System.out.println("MyCalendarController.findById");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			MyCalendar myCalendar = calendarService.findById(id);
			System.out.println("date = " + myCalendar);
		} catch (IOException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}

	private void findAll(BufferedReader reader) {
		System.out.println("BookController.findAll");
		ArrayList<MyCalendar> books = calendarService.findAll();
		if (books != null && books.size() != 0) {
			for (int i = 0; i < books.size(); i++) {
				System.out.println("date =" + books.get(i));
			}
		} else {
			System.out.println("dates empty");
		}
	}

	private void diffById(BufferedReader reader) {
		System.out.println("MyCalendarController.findById");
		try {
			System.out.println("Please, enter id first date");
			String id = reader.readLine();
			MyCalendar date1 = calendarService.findById(id);
			System.out.println("date = " + date1);

			System.out.println("Please, enter id second date");
			id = reader.readLine();
			MyCalendar date2 = calendarService.findById(id);
			differenceBetweenTwoDates(date1, date2);
			System.out.println("date = " + date2);

		} catch (IOException | NullPointerException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}

	private void addById(BufferedReader reader) {
		System.out.println("MyCalendarController.findById");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			MyCalendar myCalendar = calendarService.findById(id);
			long timeInMscDate = dateToMsc(myCalendar);
			System.out.println("Enter milliseconds");
			long milliseconds = Long.parseLong(reader.readLine());
			System.out.println("Enter seconds");
			long seconds = Long.parseLong(reader.readLine());
			System.out.println("Enter minutes");
			long minutes = Long.parseLong(reader.readLine());
			System.out.println("Enter hours");
			long hours = Long.parseLong(reader.readLine());
			System.out.println("Enter days");
			long days = Long.parseLong(reader.readLine());
			System.out.println("Enter months");
			long months = Long.parseLong(reader.readLine());
			System.out.println("Enter years");
			long years = Long.parseLong(reader.readLine());
			long timeInMscForAdd = dateToMscForAdd(milliseconds, seconds, minutes, hours, days, months, years);
			long sum = 0;
			sum = timeInMscForAdd + timeInMscDate;
			myCalendar = addAndSubToDate(myCalendar, sum);
			System.out.println("date = " + myCalendar);
		} catch (IOException | NullPointerException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}

	private void subById(BufferedReader reader) {
		System.out.println("MyCalendarController.findById");
		try {
			System.out.println("Please, enter id");
			String id = reader.readLine();
			MyCalendar myCalendar = calendarService.findById(id);
			long timeInMscDate = dateToMsc(myCalendar);
			System.out.println("Enter milliseconds");
			long milliseconds = Long.parseLong(reader.readLine());
			System.out.println("Enter seconds");
			long seconds = Long.parseLong(reader.readLine());
			System.out.println("Enter minutes");
			long minutes = Long.parseLong(reader.readLine());
			System.out.println("Enter hours");
			long hours = Long.parseLong(reader.readLine());
			System.out.println("Enter days");
			long days = Long.parseLong(reader.readLine());
			System.out.println("Enter months");
			long months = Long.parseLong(reader.readLine());
			System.out.println("Enter years");
			long years = Long.parseLong(reader.readLine());
			long timeInMscForAdd = dateToMscForAdd(milliseconds, seconds, minutes, hours, days, months, years);
			long sum = 0;
			sum = timeInMscDate - timeInMscForAdd;
			myCalendar = addAndSubToDate(myCalendar, sum);
			System.out.println("date = " + myCalendar);
		} catch (IOException | NullPointerException e) {
			System.out.println("problem: = " + e.getMessage());
		}
	}

	private void sortAsc(BufferedReader reader) {
		System.out.println(".sortAsc.");
		ArrayList<MyCalendar> books = calendarService.findAll();
		Comparator<MyCalendar> compareByName =
				Comparator.comparing(MyCalendar::getYear)
						.thenComparing(MyCalendar::getMonth)
						.thenComparing(MyCalendar::getDay)
						.thenComparing(MyCalendar::getHour)
						.thenComparing(MyCalendar::getMinute)
						.thenComparing(MyCalendar::getSecond)
						.thenComparing(MyCalendar::getMillisecond);

		Collections.sort(books, compareByName);
		System.out.println("Sorted");
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i));
		}
	}

	private void sortDesc(BufferedReader reader) {
		System.out.println(".sortDesc.");
		ArrayList<MyCalendar> books = calendarService.findAll();
		Comparator<MyCalendar> compareByName =
				Comparator.comparing(MyCalendar::getYear, Comparator.reverseOrder())
						.thenComparing(MyCalendar::getMonth, Comparator.reverseOrder())
						.thenComparing(MyCalendar::getDay, Comparator.reverseOrder())
						.thenComparing(MyCalendar::getHour, Comparator.reverseOrder())
						.thenComparing(MyCalendar::getMinute, Comparator.reverseOrder())
						.thenComparing(MyCalendar::getSecond, Comparator.reverseOrder())
						.thenComparing(MyCalendar::getMillisecond, Comparator.reverseOrder());

		Collections.sort(books, compareByName);
		System.out.println("Sorted");
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i));
		}
	}

	private void formatOutput(BufferedReader reader) {
		ArrayList<MyCalendar> books = calendarService.findAll();
		System.out.println("Format dd/mm/yyyy 01/12/2021 - 1 ");
		System.out.println("Format m/d/yyyy - 3/4/2021 - 2 ");
		System.out.println("Format mmm-d-yy - Apr 4 21 - 3 ");
		System.out.println("Format dd-mmm-yyyy 00:00 - 09 Apr 789 45:23 - 4 ");
		try {
			int format = Integer.parseInt(reader.readLine());

			if (format == 1) {
				for (int i = 0; i < books.size(); i++) {
					System.out.printf("%02d", books.get(i).getDay());
					System.out.print("/");
					System.out.printf("%02d", books.get(i).getMonth());
					System.out.print("/");
					System.out.println(books.get(i).getYear());
				}
			} else if (format == 2) {
				for (int i = 0; i < books.size(); i++) {
					System.out.println(books.get(i).getMonth() + "/" + books.get(i).getDay() + "/" + books.get(i).getYear());
				}
			} else if (format == 3) {
				for (int i = 0; i < books.size(); i++) {
					System.out.println(getMonthName(books.get(i).getMonth()) + " " + books.get(i).getDay() + " " + books.get(i).getYear());
				}
			} else if (format == 4) {
				for (int i = 0; i < books.size(); i++) {
					System.out.printf("%02d ", books.get(i).getDay());
					System.out.print(getMonthName(books.get(i).getMonth()) + " " + books.get(i).getYear() + " ");
					System.out.printf("%02d:", books.get(i).getHour());
					System.out.printf("%02d", books.get(i).getMinute());
				}
			} else {
				System.out.println("Wrong choice");
				System.out.println("Enter 1-4");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
