package com.sysvine.turbopro.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

	public static String getCurrentDate() {
		SimpleDateFormat formatter;
		Calendar cal;
		
		cal = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date currentDate = cal.getTime();
		
		String current_date = formatter.format(currentDate);
		return current_date;
	}

}
