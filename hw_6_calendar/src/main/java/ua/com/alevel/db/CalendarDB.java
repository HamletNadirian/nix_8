package ua.com.alevel.db;

import ua.com.alevel.entity.MyCalendar;
import java.util.ArrayList;
import java.util.UUID;

public class CalendarDB {
	private ArrayList<MyCalendar> calendars;
	private static CalendarDB instance;

	private CalendarDB() {
		calendars = new ArrayList<>();
	}

	public static CalendarDB getInstance() {
		if (instance == null) {
			instance = new CalendarDB();
		}
		return instance;
	}

	public void create(MyCalendar calendar) {
		try {
			calendar.setId(generateId());
			calendars.add(calendar);
		} catch (NullPointerException e) {
			System.out.println("date/time cannot be null (in this format).");
		}

	}

	public void update(MyCalendar calendar) {
		MyCalendar current = findById(calendar.getId());
		current.setYear(calendar.getYear());
		current.setMonth(calendar.getMonth());
		current.setDay(calendar.getDay());
		current.setHour(calendar.getHour());
		current.setMinute(calendar.getMinute());
		current.setSecond(calendar.getSecond());
		current.setMillisecond(calendar.getMillisecond());
	}

	public void delete(String id) {
		for (int i = 0; i < calendars.size(); i++) {
			if (calendars.get(i).getId().equals(id)) {
				calendars.remove(i);
			}
		}
	}

	public MyCalendar findById(String id) throws IndexOutOfBoundsException {
		int tempId = 0;
		try {
			tempId = searchId(id);
			return calendars.get(tempId);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Id not found");
		}
		return null;
	}

	public int searchId(String id) {
		for (int i = 0; i < calendars.size(); i++) {
			if (calendars.get(i).getId().equals(id)) {
				return (i);
			}
		}
		return -1;
	}

	public ArrayList<MyCalendar> findAll() {
		return calendars;
	}

	private String generateId() {
		String id = UUID.randomUUID().toString();
		for (int i = 0; i < calendars.size(); i++) {
			if (calendars.get(i).getId().equals(id)) {
				return generateId();
			}
		}
		return id;
	}

}
