package ua.com.alevel.util;

import ua.com.alevel.entity.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtilDateFormat {
	private static boolean testTimeFormatWithoutSecAndMsc(String time) {
		time = time.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = time.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == ':')
				c++;
		}
		if (c == 1)
			isAll = true;
		return isAll;
	}

	private static boolean testTimeFormatWithoutMinuteAndSecAndMsc(String time) {
		time = time.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = time.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == ':')
				c++;
		}
		if (c == 0)
			isAll = true;
		return isAll;
	}

	private static MyCalendar date_format_dd_mm_yyyy(String date, MyCalendar myCalendar) {
		String regex = "^\\d{1,2}/\\d{1,2}/\\d{1,4}$";
		boolean result = date.matches(regex);
		if (result) {
			date = date.replaceAll("^0+(?=.)", "");
			String[] dateArr = date.split("/");
			myCalendar.setDay(Long.parseLong(dateArr[0]));
			myCalendar.setMonth((int) Long.parseLong(dateArr[1]));
			myCalendar.setYear((int) Long.parseLong(dateArr[2]));
			myCalendar.setHour(0);
			myCalendar.setMinute(0);
			myCalendar.setSecond(0);
			myCalendar.setMillisecond(0);
		} else {
			System.out.println("Date is not valid ");
		}
		return myCalendar;
	}

	private static MyCalendar date_format_m_d_yyyy(String date, MyCalendar myCalendar) {
		//	String regex = "([1-3]?\\d/{1})([1]?\\d/{1})([12]{1}\\d{3})";
		String regex = "([1-3]?\\d/{1})([1]?\\d/{1})(\\d{1,4})";
		boolean result = date.matches(regex);
		if (result) {
			date = date.replaceAll("^0+(?=.)", "");
			String[] dateArr = date.split("/");
			myCalendar.setDay(Long.parseLong(dateArr[0]));
			myCalendar.setMonth((int) Long.parseLong(dateArr[1]));
			myCalendar.setYear((int) Long.parseLong(dateArr[2]));
			myCalendar.setHour(0);
			myCalendar.setMinute(0);
			myCalendar.setSecond(0);
			myCalendar.setMillisecond(0);
			System.out.println();
		} else {
			System.out.println("Date is not valid ");
		}
		return myCalendar;
	}

	private static MyCalendar date_format_mmm_d_yyyy(String date, MyCalendar myCalendar) {
		String regex = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) (([1-9])|([1-2][0-9])|([3][0-1])) \\d{1,4}$";
		boolean result = date.matches(regex);
		if (result) {
			date = date.replaceAll("^0+(?=.)", "");
			String[] dateArr = date.split(" ");
			myCalendar.setMonth((dateArr[0]));
			myCalendar.setDay((int) Long.parseLong(dateArr[1]));
			myCalendar.setYear((int) Long.parseLong(dateArr[2]));
			myCalendar.setHour(0);
			myCalendar.setMinute(0);
			myCalendar.setSecond(0);
			myCalendar.setMillisecond(0);
			System.out.println();
		} else {
			System.out.println("Date is not valid ");
		}
		return myCalendar;
	}

	private static MyCalendar date_format_dd_mmm_yyyy_HHMM(String date, MyCalendar myCalendar) {
		String regex = "^(([0-9])|([0-2][0-9])|([3][0-1])) ((Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{1,4}) ([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
		boolean result = date.matches(regex);
		if (result) {
			date = date.replaceAll("^0+(?=.)", "");
			String[] dateArr = date.split(" ");
			myCalendar.setDay((int) Long.parseLong(dateArr[0]));
			myCalendar.setMonth((dateArr[1]));
			myCalendar.setYear((int) Long.parseLong(dateArr[2]));
			String[] time = dateArr[3].split(":");
			myCalendar.setHour((int) Long.parseLong(time[0]));
			myCalendar.setMinute((int) Long.parseLong(time[1]));
			System.out.println();
		} else {
			System.out.println("Date is not valid ");
		}
		return myCalendar;
	}

	// 1/5/47
	public static boolean testDataFormatAll(String str) {
		str = str.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = str.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '/')
				c++;
		}
		if (c == 2 && ch[0] != '/')
			isAll = true;
		return isAll;
	}

	// /12/2008
	public static boolean testDataFormatWithoutDate(String str) {
		str = str.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = str.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '/')
				c++;
		}
		if (c == 2 && ch[0] == '/' && ch[1] != '/' && ch[ch.length - 1] != '/')
			isAll = true;
		return isAll;
	}

	// /12/
	public static boolean testDataFormatWithoutDateAndYear(String str) {
		str = str.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = str.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '/')
				c++;
		}
		if (c == 2 && ch[0] == '/' && ch[1] != '/' && ch[ch.length - 1] == '/')
			isAll = true;
		return isAll;
	}

	// //2011
	public static boolean testDataFormatWithoutDateAndMonth(String str) {
		str = str.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = str.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '/')
				c++;
		}
		if (c == 0)
			isAll = true;
		return isAll;
	}

	//00:24:00:000
	public static boolean testTimeFormatAll(String str) {
		str = str.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = str.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == ':')
				c++;
		}
		if (c == 3)
			isAll = true;
		return isAll;
	}

	//00:24
	public static boolean testTimeFormatWithoutHourAndSecAndMsc(String str) {
		str = str.replaceAll("^0+(?=.)", "");
		boolean isAll = false;
		char ch[] = str.toCharArray();
		int c = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == ':')
				c++;
		}
		if (c == 1 && ch.length < 3)
			isAll = true;
		return isAll;
	}

	public static MyCalendar input(MyCalendar myCalendar) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter default format - 1");
			System.out.println("Input in the specified format - 2");
			int format = Integer.parseInt(reader.readLine());
			System.out.println();
			if (format == 1) {
				System.out.println("Enter date in these formats");
				System.out.println("1/10/34");
				System.out.println("1/5/47 00:24:00:000");
				System.out.println("/5/47 00:24:00:000");
				System.out.println("/2/ :2");
				System.out.println("/2/ 2");
				System.out.println("1256 14:59");
				String dateAndTime = reader.readLine();
				String[] input = dateAndTime.split(" ");
				myCalendar = selectDate(input, myCalendar);

			} else if (format == 2) {
				formatDate();
				int choice = Integer.parseInt(reader.readLine());
				switch (choice) {
					case 1:
						System.out.println("Enter date in the format dd_mm_yyyy");
						System.out.println("For example: 01/12/1998");
						String date_dd_mm_yyyy = reader.readLine();
						myCalendar = date_format_dd_mm_yyyy(date_dd_mm_yyyy, myCalendar);
						System.out.println();
						break;
					case 2:
						System.out.println("Enter date in the format dd_mm_yyyy");
						System.out.println("For example: 1/12/1998");
						String date_m_d_yyyy = reader.readLine();
						myCalendar = date_format_m_d_yyyy(date_m_d_yyyy, myCalendar);
						System.out.println();
						break;
					case 3:
						System.out.println("Enter date in the format dd_mm_yyyy");
						System.out.println("For example: Apr 4 2009");
						String date_mmm_d_yyyy = reader.readLine();
						myCalendar = date_format_mmm_d_yyyy(date_mmm_d_yyyy, myCalendar);
						System.out.println();
						break;
					case 4:
						System.out.println("Enter date in the format dd_mm_yyyy");
						System.out.println("For example: 09 April 2008 21:23");
						String date_dd_mmm_yyyy_HHMM = reader.readLine();
						myCalendar = date_format_dd_mmm_yyyy_HHMM(date_dd_mmm_yyyy_HHMM, myCalendar);
						System.out.println();
						break;
				}
			} else {
				System.out.println("Wrong choice.");
				System.out.println("Please, enter format 1-2");
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("Wrong choice.");
			System.out.println("Please, enter format 1-2");
		}
		return myCalendar;
	}

	private static void formatDate() {
		System.out.println("dd/mm/yy - 01/12/2001 - 1");
		System.out.println("m/d/yyyy - 3/4/2021 - 2");
		System.out.println("mmm-d-yy - Apr 4 2011 - 3");
		System.out.println("dd-mmm-yyyy 00:00 - 09 Apr 2008 21:23 - 4");
	}

	private static MyCalendar selectDate(String[] dateAndTime, MyCalendar myCalendar) {
		try {
			if (dateAndTime.length == 1) {
				String date = dateAndTime[0];
				date = date.replaceAll("^0+(?=.)", "");
				String[] arrDate = date.split("/", 3);
				myCalendar.setDay(Long.parseLong(arrDate[0]));
				myCalendar.setMonth(Integer.parseInt(arrDate[1]));
				myCalendar.setYear(Integer.parseInt(arrDate[2]));
				myCalendar.setHour(0);
				myCalendar.setMinute(0);
				myCalendar.setSecond(0);
				myCalendar.setMillisecond(0);
			} else if (dateAndTime.length == 2) {
				String date = dateAndTime[0];
				String time = dateAndTime[1];
				if (testDataFormatAll(date)) {
					date = date.replaceAll("^0+(?=.)", "");
					String[] arrDate = date.split("/", 3);
					myCalendar.setDay(Long.parseLong(arrDate[0]));
					myCalendar.setMonth(Integer.parseInt(arrDate[1]));
					myCalendar.setYear(Integer.parseInt(arrDate[2]));
					selectTime(time, myCalendar);
					System.out.println();

				} else if (testDataFormatWithoutDate(date)) {
					String[] arrDate = date.split("/");
					myCalendar.setDay(1);
					myCalendar.setMonth(Integer.parseInt(arrDate[1]));
					myCalendar.setYear(Integer.parseInt(arrDate[2]));
					selectTime(time, myCalendar);
				} else if (testDataFormatWithoutDateAndMonth(date)) {
					String[] arrDate = date.split("/");
					myCalendar.setDay(1);
					myCalendar.setMonth(1);
					myCalendar.setYear(Integer.parseInt(arrDate[0]));
					selectTime(time, myCalendar);
				} else if (testDataFormatWithoutDateAndYear(date)) {
					String[] arrDate = date.split("/");
					myCalendar.setDay(1);
					myCalendar.setMonth(Integer.parseInt(arrDate[1]));
					myCalendar.setYear(0);
					selectTime(time, myCalendar);
				} else {
					System.out.println("Введено в неверном формате");
				}
			} else {
				System.out.println("Введено в неверном формате");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please,enter date.");
		}

		System.out.println();
		return myCalendar;
	}

	public static MyCalendar selectTime(String time, MyCalendar myCalendar) {
		if (testTimeFormatAll(time)) {
			time = time.replaceAll("^0+(?=.)", "");
			String arrTime[] = time.split(":");
			for (int i = 0; i < arrTime.length; i++) {
				if (arrTime[i].equals("")) {
					arrTime[i] = String.valueOf('0');
				}
			}
			myCalendar.setHour(Long.parseLong(arrTime[0]));
			myCalendar.setMinute(Long.parseLong(arrTime[1]));
			myCalendar.setSecond(Long.parseLong(arrTime[2]));
			myCalendar.setMillisecond(Long.parseLong(arrTime[3]));
			System.out.println();
		} else if (testTimeFormatWithoutMinuteAndSecAndMsc(time)) {
			String hour = time;
			myCalendar.setHour(Long.parseLong(hour));
			myCalendar.setMinute(0);
			myCalendar.setSecond(0);
			myCalendar.setMillisecond(0);
		} else if (testTimeFormatWithoutHourAndSecAndMsc(time)) {
			String minute = time.replaceAll(":", "");
			myCalendar.setHour(0);
			myCalendar.setMinute(Long.parseLong(minute));
			myCalendar.setSecond(0);
			myCalendar.setMillisecond(0);
		} else if (testTimeFormatWithoutSecAndMsc(time)) {
			time = time.replaceAll("^0+(?=.)", "");
			String arrTime[] = time.split(":", 2);
			myCalendar.setHour(Long.parseLong(arrTime[0]));
			myCalendar.setMinute(Long.parseLong(arrTime[1]));
			myCalendar.setSecond(0);
			myCalendar.setMillisecond(0);
			System.out.println();
		}
		System.out.println();
		return myCalendar;
	}
}
