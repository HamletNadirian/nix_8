package ua.com.alevel.util;

import ua.com.alevel.entity.MyCalendar;
import static ua.com.alevel.util.CalendarHelper.*;
import static ua.com.alevel.util.CalendarHelper.ONE_YEAR;

public class UtilDateConverter {

	public static MyCalendar addAndSubToDate(MyCalendar myCalendar, long msc) {
		long mili = Math.abs(msc) % 1000;
		long seconds = (Math.abs(msc) / 1000) % 60;
		long minutes = ((Math.abs(msc) / (1000 * 60)) % 60);
		long hours = ((Math.abs(msc) / (1000 * 60 * 60)) % 24);
		long days = (long) ((Math.abs(msc) / (1000 * 60 * 60 * 24)) % 30.41666666);
		long months = (long) ((Math.abs(msc) / (1000 * 60 * 60 * 24 * 30.41666666)) % 12);
		long years = (long) (((msc / (1000 * 60 * 60 * 24 * 30.41666666 * 12)))) % 31536000000L;

		myCalendar.setMillisecond(mili);
		myCalendar.setSecond(seconds);
		myCalendar.setMinute(minutes);
		myCalendar.setHour(hours);
		myCalendar.setDay(days);
		myCalendar.setMonth(months);
		myCalendar.setYear(years);
		return myCalendar;
	}

	public static long dateToMscForAdd(long milliseconds_add, long seconds_add, long minutes_add, long hours_add, long days_add, long moths_add, long years_add) {
		long millisecund = milliseconds_add;
		long seconds = seconds_add * ONE_SECOND;
		long minutes = minutes_add * ONE_MINUTE;
		long hours = hours_add * ONE_HOUR;
		long days = days_add * ONE_DAY;
		long months = (moths_add * ONE_MONTH);
		long years = (years_add * ONE_YEAR);
		long result = millisecund + seconds + minutes + hours + days + months + years;
		return result;
	}

	public static long dateToMsc(MyCalendar myCalendar) {
		long msc = myCalendar.getMillisecond();
		long seconds = myCalendar.getSecond() * ONE_SECOND;
		long minutes = myCalendar.getMinute() * ONE_MINUTE;
		long hours = myCalendar.getHour() * ONE_HOUR;
		long days = myCalendar.getDay() * ONE_DAY;
		//long lim = getNumberOfDaysInMonth(myCalendar.getYear(), myCalendar.getMonth());
		long months = (long) (myCalendar.getMonth() * ONE_MONTH);
		long years = (long) (myCalendar.getYear() * ONE_YEAR);
		long result = msc + seconds + minutes + hours + days + months + years;
		return result;
	}

	public static void mscToDate(long msc) {
		long mili = msc % 1000;
		long seconds = (msc / 1000) % 60;
		long minutes = ((msc / (1000 * 60)) % 60);
		long hours = ((msc / (1000 * 60 * 60)) % 24);
		long days = (long) ((msc / (1000 * 60 * 60 * 24)) % 30.41666666);
		long months = (long) ((msc / (1000 * 60 * 60 * 24 * 30.41666666)) % 12);
		long years = (long) ((msc / (1000 * 60 * 60 * 24 * 30.41666666 * 12)) % 365.25);
		System.out.println("millisecond: " + mili);
		System.out.println("seconds: " + seconds);
		System.out.println("minutes: " + minutes);
		System.out.println("hours: " + hours);
		System.out.println("days: " + days);
		System.out.println("months: " + months);
		System.out.println("years: " + years);
	}

}
