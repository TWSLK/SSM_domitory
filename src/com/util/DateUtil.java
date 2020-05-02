package com.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	/** 日期格式,指定到日期. */
	public static final String DATE_FORMAT_DATE = "yyyy-MM-dd";

	/** 日期格式,指定到时分秒. */
	public static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/** 日期格式,指定到时分. */
	public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

	/** 日期格式,指定到年. */
	public static final String DATE_FORMAT_YEAR_NAME = "yyyy";

	/** 日期格式,指定到月. */
	public static final String DATE_FORMAT_MONTH_NAME = "yyyyMM";

	/** 日期格式,指定到日. */
	public static final String DATE_FORMAT_DATE_NAME = "yyyyMMdd";

	/** 开始时间后缀 */
	public static final String TIME_START_SUFFIX = " 00:00:00";

	/** 结束时间后缀 */
	public static final String TIME_END_SUFFIX = " 23:59:59";
	
	/** 日期格式,指定到时分秒纯数字 */
	public static final String DATE_FORMAT_TIME_MILL_NUMBER = "yyyyMMddHHmmss";

	/**
	 * Instantiates a new date util.
	 */
	private DateUtil() {
		// do not
	}

	public static String getDateStrByTimestamp(Object intt) {
		String rtString = "";
		String str = String.valueOf(intt);
		if (str.matches("^[0-9]*$")) {
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_TIME);
			Timestamp tt = new Timestamp(Integer.valueOf(String.valueOf(intt)));
			rtString = format.format(tt);
		} else {
			rtString = str.substring(0,19);
		}
		return rtString;
	}
	
	/**
	 * Timestamp格式的日期转换成String字符串.
	 * 
	 * @param t
	 *            the t
	 * @return the string
	 */
	public static String timestampToDateString(Timestamp t) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DATE);
		Timestamp tt = t;
		if (tt == null) {
			tt = new Timestamp(System.currentTimeMillis());
		}
		return format.format(tt);
	}

	/**
	 * Timestamp格式的日期转换成String字符串.
	 * 
	 * @param t
	 *            the t
	 * @return the string
	 */
	public static String timestampToDateString2(Timestamp t) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_TIME);
		Timestamp tt = t;
		if (tt == null) {
			tt = new Timestamp(System.currentTimeMillis());
		}
		return format.format(tt);
	}

	/**
	 * 字符串格式的时间转换成Timestamp类型,将时间转换为Timestamp.
	 * 
	 * @param dateStr
	 *            the date str
	 * @return the timestamp
	 */
	public static Timestamp stringToTimestamp(String dateStr) {
		SimpleDateFormat sp = new SimpleDateFormat(DATE_FORMAT_TIME);
		return stringToTimestamp0(dateStr, sp);
	}

	/**
	 * 字符串格式的时间转换成Timestamp类型.该方法作为一个底层方法.
	 * 
	 * @param dateStr
	 *            the date str
	 * @param sp
	 *            the sp
	 * @return the timestamp
	 */
	private static Timestamp stringToTimestamp0(String dateStr, SimpleDateFormat sp) {
		String str = dateStr.trim();
		if (str.length() < 8) {
			return new Timestamp(System.currentTimeMillis());
		} else {
			return new Timestamp(sp.parse(dateStr, new ParsePosition(0)).getTime());
		}
	}

	/**
	 * 字符串格式的时间转换成Timestamp类型,将日期转换为Timestamp.
	 * 
	 * @param dateStr
	 *            the date str
	 * @return the timestamp
	 */
	public static Timestamp stringToTimestampWithDate(String dateStr) {
		SimpleDateFormat sp = new SimpleDateFormat(DATE_FORMAT_DATE);
		return stringToTimestamp0(dateStr, sp);
	}

	/**
	 * 获取当前时间,格式为yyyy-MM-dd HH:mm:ss.
	 * 
	 * @return the current date time
	 */
	public static String getCurrentDateTime() {
		return timestampToDatetimeString(new Timestamp(System.currentTimeMillis()));
	}
	
	public static String getCurrentDateNumber() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_TIME_MILL_NUMBER);
		return format.format(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 获取当前日期,格式为yyyy-MM-dd.
	 * 
	 * @return the current date
	 */
	public static String getCurrentDate() {
		return timestampToDateString(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss格式,格式化时间.
	 * 
	 * @param t
	 *            the t
	 * @return the string
	 */
	public static String timestampToDatetimeString(Timestamp t) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_TIME);
		return format.format(t);
	}

	/**
	 * 计算begin和end相差的天数.
	 * 
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the long
	 */
	public static long dateInterval(String begin, String end) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
		try {
			Date beginDate = formatter.parse(begin);
			Date endDate = formatter.parse(end);
			if (endDate.getTime() - beginDate.getTime() > 0) {
				return (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000l) + 1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	

	/**
	 * 计算begin和end相差的秒数.
	 * 
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the long
	 */
	public static long secondsDiff(String begin, String end) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			Date beginDate = formatter.parse(begin);  
		    Date endDate = formatter.parse(end);
		    if (endDate.getTime() - beginDate.getTime() > 0) {
				return (endDate.getTime() - beginDate.getTime()) / 1000l;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 计算begin和end相差的秒数.
	 * 
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the long
	 */
	public static long secondInterval(String begin, String end) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			Date beginDate = formatter.parse(begin);  
		    Date endDate = formatter.parse(end);
		    if (endDate.getTime() - beginDate.getTime() > 0) {
				return (endDate.getTime() - beginDate.getTime());
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 计算begin和end相差的分钟数.
	 * 
	 * @param begin
	 *            the begin
	 * @param end
	 *            the end
	 * @return the long
	 */
	public static long minuteInterval(String begin, String end) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MINUTE);
		try {
			Date beginDate = formatter.parse(begin);
			Date endDate = formatter.parse(end);
			if (endDate.getTime() - beginDate.getTime() > 0) {
				return (endDate.getTime() - beginDate.getTime()) / (60 * 1000l);
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取当前时间以前某天的日期,具体到时分秒.
	 * 
	 * @param days
	 *            the days
	 * @return the before current with days
	 */
	public static String getBeforeCurrentWithDays(int days) {
		return timestampToDatetimeString(new Timestamp(System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000l)));
	}

	/**
	 * 获取当前时间以后某天的日期,具体到时分秒.
	 * 
	 * @param days
	 *            the days
	 * @return the before current with days
	 */
	public static String getAfterCurrentWithDays(int days) {
		return timestampToDatetimeString(new Timestamp(System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000l)));
	}

	/**
	 * 获取当前时间几天以后的时间.格式为yyyy-MM-dd.
	 * 
	 * @param days
	 *            the days
	 * @return the after current with date
	 */
	public static String getAfterCurrentWithDate(int days) {
		return timestampToDateString(new Timestamp(System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000l)));
	}

	/**
	 * 获取当前时间几天以前的时间.格式为yyyy-MM-dd.
	 * 
	 * @param days
	 *            the days
	 * @return the before current with date
	 */
	public static String getBeforeCurrentWithDate(int days) {
		return timestampToDateString(new Timestamp(System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000l)));
	}

	/**
	 * 获取当前时间以前几个小时的时间.
	 * 
	 * @param hours
	 *            the hours
	 * @return 返回当前时间以前几个小时的时间戳
	 */
	public static long getBeforeCurrentWithHours(int hours) {
		return System.currentTimeMillis() - (hours * 60 * 60 * 1000l);
	}

	/**
	 * 获取当前时间以前几个小时的时间.
	 * 
	 * @param hours
	 *            the hours
	 * @return 返回当前时间以前几个小时的时间戳
	 */
	public static long getBeforeCurrentWithMinute(int minute) {
		return System.currentTimeMillis() - (minute * 60 * 1000l);
	}

	/**
	 * 获取当前小时.
	 * 
	 * @return the current hour
	 */
	public static String getCurrentHour() {
		SimpleDateFormat format = new SimpleDateFormat("HH");
		return format.format(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 得到当前的分.
	 * 
	 * @return the minus
	 */
	public static String getCurrentMinute() {
		SimpleDateFormat format = new SimpleDateFormat("mm");
		return format.format(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 获取当前的秒.
	 * 
	 * @return the current second
	 */
	public static String getCurrentSecond() {
		SimpleDateFormat format = new SimpleDateFormat("ss");
		return format.format(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 获取钱钱以前的时间.
	 * 
	 * @param hour
	 *            the hour
	 * @return the before hour
	 */
	public static String getBeforeHour(int hour) {
		SimpleDateFormat format = new SimpleDateFormat("HH");
		return format.format(new Timestamp(getBeforeCurrentWithHours(hour)));
	}

	/**
	 * 获取钱钱以前的时间.
	 * 
	 * @param hour
	 *            the hour
	 * @return the before hour
	 */
	public static String getBeforeMinus(int minute) {
		SimpleDateFormat format = new SimpleDateFormat("mm");
		return format.format(new Timestamp(getBeforeCurrentWithMinute(minute)));
	}

	/**
	 * 获取当前时间以后几个小时的时间.
	 * 
	 * @param hours
	 *            the hours
	 * @return 返回当前时间以后几个小时的时间戳
	 */
	public static long getAfterCurrentWithHours(int hours) {
		return System.currentTimeMillis() + (hours * 60 * 60 * 1000l);
	}

	/**
	 * 多少秒前或后的时间,具体到时分秒.
	 * 
	 * @param seconds
	 *            秒数
	 * @return the time for interval
	 */
	public static String getTimeForInterval(int seconds) {
		return timestampToDatetimeString(new Timestamp(System.currentTimeMillis() + (seconds * 1000l)));
	}

	/**
	 * 获取通过日期作为名称的情况.格式为yyyyMM,如201207
	 * 
	 * @param t
	 *            the t
	 * @param formatter
	 *            the formatter
	 * @return the date for name
	 */
	public static String getDateForName(Timestamp t, SimpleDateFormat formatter) {
		return formatter.format(t);
	}

	/**
	 * 获取以今年为后缀名称的字符串,例如2012.
	 * 
	 * @return string
	 */
	public static String getCurrentDateForName2() {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_YEAR_NAME);
		return getDateForName(new Timestamp(System.currentTimeMillis()), formatter);
	}

	/**
	 * 获取以今天为名称的字符串,例如201207.
	 * 
	 * @return the current date for name
	 */
	public static String getCurrentDateForName() {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MONTH_NAME);
		return formatter.format(new Date());
	}

	/**
	 * 获取以今天为名称的字符串,例如20120701.
	 * 
	 * @return the current date for name1
	 */
	public static String getCurrentDateForName1() {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE_NAME);
		return getDateForName(new Timestamp(System.currentTimeMillis()), formatter);
	}

	/**
	 * 获取几天后要读的表后缀,例如格式为yyyyMM.
	 * 
	 * @param day
	 *            the day
	 * @return the after days table sub
	 */
	public static String getAfterDaysTableSub(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MONTH_NAME);
		Timestamp temp = stringToTimestamp(getAfterCurrentWithDays(day));
		return getDateForName(temp, formatter);
	}

	/**
	 * 获取几天前要读的表后缀,例如格式为yyyyMM.
	 * 
	 * @param day
	 *            the day
	 * @return the after days table sub
	 */
	public static String getBeforeDaysTableSub(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MONTH_NAME);
		Timestamp temp = stringToTimestamp(getBeforeCurrentWithDays(day));
		return getDateForName(temp, formatter);
	}

	/**
	 * 获取当前之前的时间.返回的格式为yyyyMMdd.
	 * 
	 * @param days
	 *            the days
	 * @return the before current with days for name
	 */
	public static String getBeforeCurrentWithDaysForName(int days) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE_NAME);
		return getDateForName(new Timestamp(System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000l)), formatter);
	}

	/**
	 * 获取当前之后的时间.返回的格式为yyyyMMdd.
	 * 
	 * @param days
	 *            the days
	 * @return the before current with days for name
	 */
	public static String getAfterCurrentWithDaysForName(int days) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE_NAME);
		return getDateForName(new Timestamp(System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000)), formatter);
	}

	/**
	 * 获取某月中的天数 .
	 * 
	 * @param year
	 *            年份 2012 2013
	 * @param month
	 *            月份 09 10
	 * @return the days of month
	 */
	public static int getDaysOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		int days = cal.get(Calendar.DAY_OF_MONTH);
		return days;
	}

	/**
	 * 获取当月的天数.
	 * 
	 * @return the days of current month
	 */
	public static int getDaysOfCurrentMonth() {
		String ymd = getCurrentDate();
		return getDaysOfMonth(ymd.substring(0, 4), ymd.substring(5, 7));
	}

	/**
	 * 判断一个日期字符串是否是某号.
	 * 
	 * @param date
	 *            the date
	 * @param day
	 *            the day
	 * @return true, if is some day
	 */
	public static boolean isSomeDay(String date, int day) {
		if (date == null || date.equals("") || day <= 0 || day > 31) {
			throw new IllegalArgumentException("参数传入的不正确!{date:" + date + ",day:" + day + "}");
		}
		String[] dateArr = date.split("-");
		String temp = dateArr[dateArr.length - 1];
		if (Integer.parseInt(temp) == day) {
			return true;
		}
		return false;
	}

	/**
	 * 判断今天是否是1号.
	 * 
	 * @return true, if is first date with current date
	 */
	public static boolean isFirstDateWithCurrentDate() {
		return isSomeDay(getCurrentDate(), 1);
	}

	/**
	 * 获取某个月之前或者之后的月份.返回Date类型的数据.
	 * 
	 * @param day
	 *            the day
	 * @return the before month with current
	 */
	public static Date getMonthWithCurrent(int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, day);
		return cal.getTime();
	}

	/**
	 * 获取当前月份之前或者之后的某个月.返回yyyyMM格式的字符串.
	 * 
	 * @param day
	 *            the day
	 * @return the month with current for month
	 */
	public static String getMonthWithCurrentForMonth(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MONTH_NAME);
		return getDateForName(new Timestamp(getMonthWithCurrent(day).getTime()), formatter);
	}

	/**
	 * 获取当前月份之前或者之后的某个月.返回yyyyMMdd格式的字符串.
	 * 
	 * @param day
	 *            the day
	 * @return the month with current for date
	 */
	public static String getMonthWithCurrentForDate(int day) {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
		return getDateForName(new Timestamp(getMonthWithCurrent(day).getTime()), formatter);
	}

	/**
	 * 获取当前月的前一个月.格式为yyyyMM.
	 * 
	 * @return the previous month with current
	 */
	public static String getPreviousMonthWithCurrent() {
		return getMonthWithCurrentForMonth(-1);
	}

	/**
	 * 获取当前月的前一个月.格式为yyyyMMdd.
	 * 
	 * @return the previous month with current date
	 */
	public static String getPreviousMonthWithCurrentDate() {
		return getMonthWithCurrentForDate(-1);
	}

	/**
	 * 获取当前月的后一个月.格式为yyyyMM.
	 * 
	 * @return the next month with current
	 */
	public static String getNextMonthWithCurrent() {
		return getMonthWithCurrentForMonth(1);
	}

	/**
	 * 获取当前月的后一个月.返回日期字符格式为yyyyMMdd.
	 * 
	 * @return the next month with current date
	 */
	public static String getNextMonthWithCurrentDate() {
		return getMonthWithCurrentForDate(1);
	}

	/**
	 * 获取上个月的第一天.格式为yyyyMMdd.如果格式改变方法需要改变.
	 * 
	 * @return the previous month first date
	 */
	public static String getPreviousMonthFirstDate() {
		return getPreviousMonthWithCurrent() + "01";
	}

	/**
	 * 获取上个月的最后一天.格式为yyyyMMdd.如果格式改变方法需要改变.
	 * 
	 * @return the previous month lash date
	 */
	public static String getPreviousMonthLastDate() {
		String ymd = getPreviousMonthWithCurrentDate();

		return getPreviousMonthWithCurrent() + getDaysOfMonth(ymd.substring(0, 4), ymd.substring(4, 6));
	}

	/**
	 * 获取下个月的第一天.格式为yyyyMMdd.如果格式改变方法需要改变.
	 * 
	 * @return the previous month first date
	 */
	public static String getNextMonthFirstDate() {
		return getNextMonthWithCurrent() + "01";
	}

	/**
	 * 获取下个月的最后一天.格式为yyyyMMdd.如果格式改变方法需要改变.
	 * 
	 * @return the previous month lash date
	 */
	public static String getNextMonthLastDate() {
		String ymd = getNextMonthWithCurrentDate();
		return getNextMonthWithCurrent() + getDaysOfMonth(ymd.substring(0, 4), ymd.substring(4, 6));
	}

	/**
	 * 获取指定日期后X天的日期.
	 * 
	 * @param dateStr
	 *            the date str
	 * @param days
	 *            the days
	 * @return the after date for days
	 */
	public static String getAfterDateForDays(String dateStr, int days) {
		long dateTimeMillis;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			dateTimeMillis = formatter.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return timestampToDateString(new Timestamp(dateTimeMillis + (days * 24 * 60 * 60 * 1000l)));
	}
	
	
	/**
	 * 获取指定日期后X天的日期.
	 * 
	 * @param dateStr
	 *            the date str
	 * @param days
	 *            the days
	 * @return the after date for days
	 */
	public static String getAfterDayForDays(String dateStr, int days) {
		long dateTimeMillis;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
		try {
			dateTimeMillis = formatter.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return timestampToDateString(new Timestamp(dateTimeMillis + (days * 24 * 60 * 60 * 1000l)));
	}
	/**
	 * 获取指定日期前X天的日期.
	 * leaf 2017年12月24日 11:16:42
	 * @param dateStr
	 *            the date str
	 * @param days
	 *            the days
	 * @return the before date for days
	 */
	public static String getBeforeDayForDays(String dateStr, int days) {
		long dateTimeMillis;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATE);
		try {
			dateTimeMillis = formatter.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return timestampToDateString(new Timestamp(dateTimeMillis - (days * 24 * 60 * 60 * 1000l)));
	}
	
	
	/**
	 * 获取某个时间以后几个小时的时间.
	 * 
	 * @param hours
	 *            the hours 
	 * @return 返回当前时间以后几个小时的时间戳
	 */
	public static String getAftetDateWithHours(String dateStr, int hours) {
		long dateTimeMillis;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			dateTimeMillis = formatter.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return timestampToDatetimeString(new Timestamp(dateTimeMillis + (hours * 60 * 60 * 1000l)));
	}
	
	/**
	 * 获取某个时间以后几分钟的时间.
	 * @param dateStr (yyyy-MM-dd HH:mm:ss)
	 * @param minute (正数为N分钟后，  负数为N分钟之前)
	 * @return
	 */
	public static String getAfterDateWithMinute(String dateStr, int minute) {
		long dateTimeMillis;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			dateTimeMillis = formatter.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return timestampToDatetimeString(new Timestamp(dateTimeMillis + (minute * 60 * 1000l)));
	}
	
	/**
	 * 获取某个时间以后几秒钟的时间.
	 * @param dateStr (yyyy-MM-dd HH:mm:ss)
	 * @param minute (正数为N秒后，  负数为N秒之前)
	 * @return
	 */
	public static String getAfterDateWithSecond(String dateStr, int second) {
		long dateTimeMillis;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
		try {
			dateTimeMillis = formatter.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return timestampToDatetimeString(new Timestamp(dateTimeMillis + (second * 1000l)));
	}

	public static int getCurrentAgeByBirthdate(String brithday){
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			String currentTime = formatDate.format(calendar.getTime());
			Date today = formatDate.parse(currentTime);
			Date brithDay = formatDate.parse(brithday);
 
			return today.getYear() - brithDay.getYear();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getAge(String strOfBirth) {
		int age = 0;
		try {
			Date dateOfBirth = null;
			if(strOfBirth==null){
				strOfBirth = "";
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateOfBirth = (Date) dateFormat.parse(strOfBirth);
			
			Calendar born = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
			if (dateOfBirth != null) {
				now.setTime(new Date());
				born.setTime(dateOfBirth);
				if (born.after(now)) {
					age = 0;
				}else {
					age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
					if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
						age -= 1;
					}
				}
			}
		} catch (ParseException e) {
			age = 0;
		}
		if(age>120){
			age = 0;
		}
		return age;
	}

	
	/**
     * 生成n位的随机数
     *
     * @param n 随机数的长度
     * @return
     */
    public static long getRandNumber(int length) {
        if (length < 1) {
        	length=4;
        }
        double b = (double) (length - 1);
        return (long) (Math.random() * 9 * Math.pow(10, b)) + (long) Math.pow(10, b);
    }
}