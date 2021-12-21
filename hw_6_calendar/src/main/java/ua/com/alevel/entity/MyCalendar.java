package ua.com.alevel.entity;

import java.util.Objects;

public class MyCalendar {

	long millisecond;
	long second;
	long minute;
	long hour;
	long day;
	long month;
	long year;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MyCalendar that = (MyCalendar) o;
		return millisecond == that.millisecond && second == that.second && minute == that.minute && hour == that.hour && day == that.day && month == that.month && year == that.year && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(millisecond, second, minute, hour, day, month, year, id);
	}

	@Override
	public String toString() {
		return "MyCalendar{" +
				"millisecond=" + millisecond +
				", second=" + second +
				", minute=" + minute +
				", hour=" + hour +
				", day=" + day +
				", month=" + month +
				", year=" + year +
				", id='" + id + '\'' +
				'}';
	}


	public long getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(long millisecond) {
		if (millisecond >= 0 && millisecond <= 1000)
			this.millisecond = millisecond;
		else
			System.out.println("The millisecond are not correct");
	}

	public long getSecond() {
		return second;
	}

	public void setSecond(long second) {
		if (second >= 0 && second <= 60)
			this.second = second;
		else
			System.out.println("The second are not correct");

	}

	public long getMinute() {
		return minute;
	}

	public void setMinute(long minute) {
		if (minute >= 0 && minute <= 60)
			this.minute = minute;
		else
			System.out.println("The minute are not correct");

	}

	public long getHour() {
		return hour;
	}

	public void setHour(long hour) {
		if (hour >= 0 && hour <= 23)
			this.hour = hour;
		else
			System.out.println("The hour are not correct");
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
		long limit = getNumberOfDaysInMonth(this.year, this.month);
		if (this.month < 1 || this.month > 12) {
			System.out.println("Wrong input month!");
			this.month = 1;
		} else if (this.day > limit) {
			this.day = limit;
			System.out.println("This month has fewer days");
		}
	}

	public void setMonth(String strTomonth) {
		int month = 0;
		switch (strTomonth) {
			case "Jan":
				month = 1;
				break;
			case "Feb":
				month = 2;
				break;
			case "Mar":
				month = 3;
				break;
			case "Apr":
				month = 4;
				break;
			case "May":
				month = 5;
				break;
			case "Jun":
				month = 6;
				break;
			case "Jul":
				month = 7;
				break;
			case "Aug":
				month = 8;
				break;
			case "Sep":
				month = 9;
				break;
			case "Oct":
				month = 10;
				break;
			case "Nov":
				month = 11;
				break;
			case "Dec":
				month = 12;
				break;
		}
		this.month = month;
		long limit = getNumberOfDaysInMonth(this.year, this.month);
		if (this.month < 1 || this.month > 12) {
			System.out.println("Wrong input month!");
			this.month = 1;
		} else if (this.day > limit) {
			this.day = limit;
			System.out.println("This month has fewer days");
			this.month = 1;

		}
	}

	public static String getMonthName(long month) {
		String monthName = switch ((int) month) {
			case 1 -> "January";
			case 2 -> "February";
			case 3 -> "March";
			case 4 -> "April";
			case 5 -> "May";
			case 6 -> "June";
			case 7 -> "July";
			case 8 -> "August";
			case 9 -> "September";
			case 10 -> "October";
			case 11 -> "November";
			case 12 -> "December";
			default -> null;
		};
		return monthName;
	}

	public static long getNumberOfDaysInMonth(long year, long month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 ||
				month == 8 || month == 10 || month == 12)
			return 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;
		if (month == 2) return isLeapYear((int) year) ? 29 : 28;
		return 0;
	}

	long getDaysInMonth(long year, long month) {
		int[] monthlengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if ((month == 1) && leapYear((int) year) == 1) {
			return 29;
		} else {
			return monthlengths[(int) month];
		}
	}

	static boolean isLeapYear(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

	int leapYear(int year) {
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
			return 1;
		} else {
			return 0;
		}
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public MyCalendar(long day, int month, int year, long hour, long minute, long second, long millisecond) {
		this.millisecond = millisecond;
		this.second = second;
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public MyCalendar() {
	}


}
