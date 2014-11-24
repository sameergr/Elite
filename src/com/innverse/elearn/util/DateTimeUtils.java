package com.innverse.elearn.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.innverse.elearn.contants.Constants;



public class DateTimeUtils {

	public static SimpleDateFormat sdfWithTZ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	public static SimpleDateFormat sdfWithoutTZ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z");
	
	public static void printDateUTCFormat(Calendar time){
		System.out.println("UTC Time :: " + sdfWithTZ.format(time.getTime())); 
	}
	
	public static String getGMTDateTimeString(Date date,String timeString,String timeZone, SimpleDateFormat sdf){
		String splitTime[] = timeString.split(":");
		
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitTime[0]));
		currentDate.set(Calendar.MINUTE, Integer.valueOf(splitTime[1]));
		currentDate.set(Calendar.SECOND, Integer.valueOf(splitTime[2]));
		currentDate.setTimeZone(TimeZone.getTimeZone(timeZone));
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(sdf.format(currentDate.getTime()));
		return sdf.format(currentDate.getTime());
	}
	
	public static Date getGMTDate(Date date, SimpleDateFormat sdf){
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println( sdf.format(dateTime.getTime()));
		return dateTime.getTime();
	}
	
	public static Calendar getGMTCalendar(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		return cal;
	}
	
	public static SimpleDateFormat getGMTSimpleDateFormat(SimpleDateFormat sdf){
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf;
	}
	
	public static void main(String[] args) {
		
		getGMTDate(new Date(),sdfWithTZ);
		
		getGMTDateTimeString(new Date(),"13:00:00","IST",sdfWithTZ);
		
		sdfWithTZ.setTimeZone(TimeZone.getTimeZone("UTC"));
		printDateUTCFormat(Calendar.getInstance());
		
		SimpleDateFormat lockTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar lockTime = Calendar.getInstance();
		lockTime.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		lockTime.add(Calendar.MINUTE, Constants.LOCK_TIME);
		lockTimeSDF.setCalendar(lockTime);
		lockTimeSDF.setTimeZone(TimeZone.getTimeZone("UTC"));

		System.out.println(lockTimeSDF.format(lockTime.getTime()));
	}
}
