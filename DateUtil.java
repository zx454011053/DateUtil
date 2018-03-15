package com.blackmcat.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/***
 * 时间帮助类
 */
public class DateUtil {

	
	//毫秒级常用常量
	public static final long SECOND_S = 1000L;
	public static final long MINUTE_S = 60000L;
	public static final long HOUR_S = 3600000L;
	public static final long DAY_S = 86400000L;
	
	//时间常量  （以下常量来自 Calendar类，我只取了一部分常用的，用于此帮助类，如有兴趣可以去阅读源码）
	public final static int YEAR = 1; //年
	public final static int MONTH = 2; //月
	public final static int DATE = 5;//天
	public final static int HOUR = 10;//小时
	public final static int MINUTE = 12;//分
	public final static int SECOND = 13;//秒
	public final static int MILLISECOND = 14;//毫秒
	
	//常用的format常量
	public final static String TIME_BEGIN = " 00:00:00";
	public final static String TIME_END = " 23:59:59";
	public static final String TRADITION_PATTERN = "yyyy-MM-dd";
	public static final String FULL_TRADITION_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String FULL_TRADITION_PATTERN_BEGIN = "yyyy-MM-dd 00:00:00";
	public static final String FULL_TRADITION_PATTERN_END = "yyyy-MM-dd 23:59:59";
	
	//获取今天的日期
	public static String getShortNow() {
		return formatDate("yyyy-MM-dd");
	}
	//获取今天开始的时间
	public static String getDayBegin() {
		return formatDate("yyyy-MM-dd") + " 00:00:00";
	}
	//获取今天结束的时间
	public static String getDayEnd() {
		return formatDate("yyyy-MM-dd") + " 23:59:59";
	}
	
	/***
	 * 获取现在的时间
	 * @return
	 */
	public static String getNow() {
		return formatDate(FULL_TRADITION_PATTERN);
	}
	
	/***
	 * 获取一天中的开始
	 * ps 2018-01-01 00:00:00
	 * @param date
	 * @return date
 	 */
	public static Date getDayBegin(Date date){
		return parseDate(formatDate(date, FULL_TRADITION_PATTERN_BEGIN), FULL_TRADITION_PATTERN_BEGIN);
	}
	/***
	 * 获取一天中的结束
	 * ps 2018-01-01 23:59:59
	 * @param date
	 * @return date
 	 */
	public static Date getDayEnd(Date date){
		return parseDate(formatDate(date, FULL_TRADITION_PATTERN_END), FULL_TRADITION_PATTERN_END); 
	}
	
	
	
	//获取单个时间
	public static String getYear(){
		return formatDate("yyyy");
	}
	public static String getMonth(){
		return formatDate("MM");
	}
	public static String getDay(){
		return formatDate("dd");
	}
	public static String getHour(){
		return formatDate("HH");
	}
	public static String getMinute(){
		return formatDate("mm");
	}
	public static String getSecond(){
		return formatDate("ss");
	}
	
	/***
	 * 对date增加 months个月。传负数获取date前months个月
	 * @param date 时间
	 * @param months int 多少个月，可传负数
	 * @return date
	 */
	public static Date addMonths(Date date, int months) {
		if (months == 0)
			return date;
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(MONTH, months);
		return cal.getTime();
	}
	
	/***
	 * 对Date增加 days天。传负数获取Date前days天
	 * @param date
	 * @param days int 多少天，可传负数
	 * @return date
	 */
	public static Date addDays(Date date, int days) {
		if (days == 0)
			return date;
		if (date == null)
			return null;
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(DATE, days);

		return new Date(cal.getTime().getTime());
	}
	
	/**
	 * 小时
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		if (hour == 0)
			return date;
		if (date == null)
			return null;
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(HOUR, hour);

		return new Date(cal.getTime().getTime());
	}
	/**
	 * 分
	 * @param date
	 * @param mins
	 * @return
	 */
	public static Date addMins(Date date, int mins) {
		if (mins == 0)
			return date;
		if (date == null)
			return null;
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(MINUTE, mins);

		return new Date(cal.getTime().getTime());
	}
	
	/***
	 * 两个时间是否在同一个月
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		if ((date1 == null) && (date2 == null))
			return true;
		if ((date1 == null) || (date2 == null))
			return false;
		Calendar cal1 = GregorianCalendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = GregorianCalendar.getInstance();
		cal2.setTime(date2);
		return isSameMonth(cal1, cal2);
	}

	/***
	 * 两个时间是否在同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if ((date1 == null) && (date2 == null))
			return true;
		if ((date1 == null) || (date2 == null))
			return false;
		Calendar cal1 = GregorianCalendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = GregorianCalendar.getInstance();
		cal2.setTime(date2);

		return (cal1.get(1) == cal2.get(1)) && (cal1.get(2) == cal2.get(2))
				&& (cal1.get(5) == cal2.get(5));
	}
	
	public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
		if ((cal1 == null) && (cal2 == null))
			return true;
		if ((cal1 == null) || (cal2 == null))
			return false;
		return (cal1.get(1) == cal2.get(1)) && (cal1.get(2) == cal2.get(2));
	}
	/***
	 * 时间转化String的常用方法（没有为空和错误判定，需要请自己加上）
	 * @param format
	 * @return String
	 */
	public static String formatDate(String format) {
		return formatDate(new Date(),format);
	}
	
	/***
	 * 时间转化String的常用方法（没有为空和错误判定，需要请自己加上）
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/***
	 * 将String类型的时间转换成功Date类型
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date parseDate(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
