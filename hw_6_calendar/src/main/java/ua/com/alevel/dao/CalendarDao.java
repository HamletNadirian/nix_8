package ua.com.alevel.dao;
import java.util.ArrayList;
import ua.com.alevel.db.CalendarDB;
import ua.com.alevel.entity.MyCalendar;


public class CalendarDao {
	public void create(MyCalendar calendar) {
		CalendarDB.getInstance().create(calendar);
	}

	public void update(MyCalendar calendar) {
		CalendarDB.getInstance().update(calendar);
	}

	public void delete(String id) {
		CalendarDB.getInstance().delete(id);
	}

	public MyCalendar findById(String id) {
		return CalendarDB.getInstance().findById(id);
	}

	public ArrayList<MyCalendar> findAll() {
		return CalendarDB.getInstance().findAll();
	}
}
