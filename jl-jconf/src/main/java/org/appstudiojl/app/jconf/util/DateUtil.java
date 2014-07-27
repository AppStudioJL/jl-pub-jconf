package org.appstudiojl.app.jconf.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <code>DateUtil</code> Contains utility for Date including:
 * <p>1) get today</p>
 * <p>2) Gets sub-byte[] from a given byte[]</p>
 * @author	Jasonwaiting
 * @version	1.0.0
 * @since 2012-08-25
 */
public class DateUtil {

	private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getDefault();

	/**
	 * <code>getToday: </code> get today in Date format
	 * @return today in Date format
	 */
	public static Date getToday (){
		return new Date ();
	}

	/**
	 * <code>toCalendar (date, timeZome):</code> convert Date to Calendar
	 * @param date	Date
	 * @param zone	timeZone (default if null)
	 * @return Calendar format
	 */
	public static Calendar toCalendar (Date date, TimeZone zone){
		if (date == null) return null;
		if (zone == null) zone = DateUtil.DEFAULT_TIMEZONE;
		Calendar cal = DateUtil.getTime(zone);
		cal.setTime(date);
		return cal;
	}

	/**
	 * <code>toCalendar (date, timeZome):</code> convert SQL Date to Calendar
	 * @param date	Date
	 * @param zone	timeZone (default if null)
	 * @return Calendar format
	 */
	public static Calendar toCalendar (java.sql.Date date, TimeZone zone){
		if (date == null) return null;
		if (zone == null) zone = DateUtil.DEFAULT_TIMEZONE;
		Calendar cal = DateUtil.getTime(zone);
		cal.setTime(date);
		return cal;
	}

	/**
	 * <code>toSqlDate (date):</code> convert Date to SQL Date
	 * @param date	Date
	 * @return SQL Date
	 */
	public static java.sql.Date toSqlDate (Date date){
		if (date == null) return null;
		java.sql.Date sqlDate = new java.sql.Date (date.getTime());
		return sqlDate;
	}

	/**
	 * <code>toSqlDate (cal):</code> convert Calendar to SQL Date
	 * @param cal	Calendar
	 * @return SQL Date
	 */
	public static java.sql.Date toSqlDate (Calendar cal){
		if (cal == null) return null;
		java.sql.Date sqlDate = new java.sql.Date (cal.getTime().getTime());
		return sqlDate;
	}

	/**
	 * <code>toDate (date): </code> convert SQL Date to Date
	 * @param date	SQL Date
	 * @return Date
	 */
	public static Date toDate (java.sql.Date date){
		if (date == null) return null;
		Date javaDate = new Date (date.getTime());
		return javaDate;
	}

	/**
	 * <code>toDate (cal):</code> convert Calendar to Date
	 * @param cal	Calendar
	 * @return Date
	 */
	public static Date toDate (Calendar cal){
		if (cal == null) return null;
		return cal.getTime();
	}

	/**
	 * <code>getTime (timezone):</code> get the time now
	 * @param zone	time zone
	 * @return Calendar instance
	 */
	public static Calendar getTime (TimeZone zone){
		return Calendar.getInstance(zone);
	}

	/**
	 * <code>addDate (date, shift):</code> add shift to Date
	 * @param date	original Date
	 * @param shift	day shift, positive value means forward shifting
	 * @return shifted date
	 */
	public static Date addDate (Date date, int shift){
		Calendar cal = DateUtil.toCalendar(date,null);
		cal = DateUtil.addDate(cal, shift);
		return DateUtil.toDate(cal);
	}

	/**
	 * <code>addDate (date, shift):</code> add shift to Calendar
	 * @param date	Calendar Date
	 * @param shift	day shift, positive means forward shifting
	 * @return  shifted date in Calendar
	 */
	public static Calendar addDate (Calendar date, int shift){
		if (date == null) return null;
		return DateUtil.addDate(date, shift, Calendar.DAY_OF_MONTH);
	}

	/**
	 * <code>addDate (date, shift, shiftField):</code> add shift to any Calendar unit
	 * @param date	Calendar Date
	 * @param shift	Unit shift
	 * @param shiftField	shift unit
	 * @return	shifted date in Calendar
	 */
	public static Calendar addDate (Calendar date, int shift, int shiftField){
		if (date == null)  return null;
		date.add(shiftField, shift);
		return date;
	}
}
