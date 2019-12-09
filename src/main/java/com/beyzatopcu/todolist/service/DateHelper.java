package com.beyzatopcu.todolist.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateHelper {
	
	private String pattern = "dd-MM-yyyy";
	private SimpleDateFormat format;
	
	public DateHelper() {
		format = new SimpleDateFormat(pattern);
	}
	
	public String convertDateToString(Date date) {
		return format.format(date);
	}
	
	public Date convertStringToDate(String date) {
		try {
			return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
