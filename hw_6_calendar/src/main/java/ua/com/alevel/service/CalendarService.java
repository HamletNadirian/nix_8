package ua.com.alevel.service;

import ua.com.alevel.entity.MyCalendar;
import ua.com.alevel.dao.CalendarDao;
import java.util.ArrayList;

public class CalendarService {
	private final CalendarDao calendarDao = new CalendarDao();

	public void create(MyCalendar calendar) {
		calendarDao.create(calendar);
	}

	public void update(MyCalendar calendar) {
		calendarDao.update(calendar);
	}

	public void delete(String id) {
		calendarDao.delete(id);
	}

	public MyCalendar findById(String id) {
		return calendarDao.findById(id);
	}

	public ArrayList<MyCalendar> findAll() {
		return calendarDao.findAll();
	}
}
