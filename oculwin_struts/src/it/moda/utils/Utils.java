package it.moda.utils;

import it.moda.dto.CalendarioDTO;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.log4j.Logger;

public class Utils {
	private static Logger log = Logger.getLogger(Utils.class);

	public static final int ETA = 5; 

	public static java.util.Date parseDate(String date){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			//log.error(e.getMessage(),e);
			return null;
		}
	}
	public static String formatDate(java.util.Date date){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.applyLocalizedPattern("dd MMMM yyyy");
		try {
			return date!=null?sdf.format(date):"";
		} catch (Exception e) {
			//log.error(e.getMessage(),e);
			return "";
		}
	}
	public static String formatDateForInput(java.util.Date date){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.applyLocalizedPattern("yyyy-MM-dd");
		try {
			return date!=null?sdf.format(date):"";
		} catch (Exception e) {
			//log.error(e.getMessage(),e);
			return "";
		}
	}
	public static String marshall(JAXBContext jc, CalendarioDTO calendarioDTO)
			throws JAXBException, PropertyException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(calendarioDTO, out);
		return out.toString();
	}
	public static int calculateAge(Date dateOfBirth){
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		int factor = 0; 
		if(dateOfBirth!=null){
			cal1.setTime(dateOfBirth);
			if(cal2.get(Calendar.DAY_OF_YEAR) < cal1.get(Calendar.DAY_OF_YEAR)) {
				factor = -1; 
			}
			return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR) + factor;
		}
		else
			return 6;
	}
}
