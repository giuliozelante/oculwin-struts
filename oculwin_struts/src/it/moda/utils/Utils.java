package it.moda.utils;

import java.text.ParseException;

import org.apache.log4j.Logger;

public class Utils {
	private static Logger log = Logger.getLogger(Utils.class);
	
	public static java.util.Date parseDate(String date){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage(),e);
			return null;
		}
	}
	public static String formatDate(java.util.Date date){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.applyLocalizedPattern("dd MMMM yyyy");
		try {
			return sdf.format(date);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return "";
		}
	}
	
	
}
