package ua.com.alevel.util;

import ua.com.alevel.entity.MyCalendar;
import static ua.com.alevel.util.UtilDateConverter.dateToMsc;
import static ua.com.alevel.util.UtilDateConverter.mscToDate;

public class Calculation {
	public static long differenceBetweenTwoDates(MyCalendar firstDate, MyCalendar secondDate) {
		long mscDateOne = dateToMsc(firstDate);
		long mscDateTwo = dateToMsc(secondDate);
		long result = 0;
		if (mscDateOne > mscDateTwo)
			result = mscDateOne - mscDateTwo;
		else if (mscDateOne < mscDateTwo) {
			result = mscDateTwo - mscDateOne;
		} else {
			System.out.println("dates equals");
			result = 0;
		}
		mscToDate(result);
		return result;
	}

}
