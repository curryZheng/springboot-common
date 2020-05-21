package com.ygxc.aqjy.common.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;


/**
 * 时间工具类
 * @author leiZheng  
 * @date 2019年6月17日
 */
public class DateUtil {


	/**
	 * 日期格式转换器
	 */
	private static Map<String, SimpleDateFormat> formatterMap = new HashMap<String, SimpleDateFormat>();
	
	/** 日期格式化格式 - yyyy-MM-dd */
	public final static String PATTERN_DATE = "yyyy-MM-dd";
	
	/** 日期格式化格式 - yyyy-MM-dd HH:mm:ss */
	public final static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	/** 日期格式化格式 - yyyy-MM-dd HH:mm:ss.SSS */
	public final static String PATTERN_DATETIME_LONG = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/** 日期格式化格式 - HH:mm:ss */
	public final static String PATTERN_TIME = "HH:mm:ss";
	
	/** 日期格式化格式 - yyyy-MM-dd HH:mm */
	public final static String PATTERN_DATETIME_MM = "yyyy-MM-dd HH:mm";
	
	/** 日期格式化格式 - yyyy年MM月dd日 */
	public final static String PATTERN_CN_DATE = "yyyy年MM月dd日";
	
	/** 日期格式化格式 - yyyy年MM月dd日 HH时mm分ss秒 */
	public final static String PATTERN_CN_DATETIME = "yyyy年MM月dd日 HH时mm分ss秒";
	
	/** 日期格式化格式 - HH时mm分ss秒 */
	public final static String PATTERN_CN_TIME = "HH时mm分ss秒";
	
	/** 日期格式化格式 - yyyyMMdd */
	public final static String PATTERN_DATE_NO_HYPHEN = "yyyyMMdd";
	
	/** 用于json的反序列化，日期的转换，表达式列表 */
	public static final String[] jsonDeserializePatternArr = new String[] {"yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss"
			, "yyyy-MM-dd HH:mm", "yyyy-MM-dd"};
	
	/**
	 * 取得当前时间
	 * @return
	 */
	public static Date getCurDate() {
		return new Date();
	}
	
	/**
	 * 取得当前时间，只取年月日
	 * 
	 * @return
	 */
	public static Date getCurBasicDate() {
		Calendar calendar = Calendar.getInstance();
		// 将时分秒,毫秒域清零
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getCurMonthDate() {
		Calendar calendar = Calendar.getInstance();
		// 将时分秒,毫秒域清零
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 取得当前时间，只取年月日
	 * 
	 * @return
	 */
	public static String getCurBasicDateStr() {
		return format(new Date(), PATTERN_DATE);
	}
	
	/**
	 * 取得当前时间
	 * @return
	 */
	public static Timestamp getCurTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 将日期转换为字符串
	 * @param date 日期
	 * @param pattern 格式字符串
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null) return null;
		
		return getFormatter(pattern).format(date);
	}
	
	
	/**
	 * 将字符串转换为日期
	 * @param dateStr 日期字符串
	 * @param pattern 格式字符串
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		try {
			return getFormatter(pattern).parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将日期转换为字符串，格式：yyyy-MM-dd
	 * @param date 日期
	 * @return
	 */
	public static String formatDate(Date date) {
		return format(date, PATTERN_DATE);
	}
	
	/**
	 * 将日期转换为字符串，格式：yyyy-MM-dd HH:mm:ss
	 * @param date 日期
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format(date, PATTERN_DATETIME);
	}
	
	/**
	 * 将日期转换为字符串，格式：HH:mm:ss
	 * @param date 日期
	 * @return
	 */
	public static String formatTime(Date date) {
		return format(date, PATTERN_TIME);
	}
	
	/**
	 * 将日期转换为字符串，格式：yyyyMMdd
	 * @param date 日期
	 * @return
	 */
	public static String formatDateNoHyphen(Date date) {
		return format(date, PATTERN_DATE_NO_HYPHEN);
	}
	
	/**
	 * 将字符串转换为日期，格式：yyyy-MM-dd
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parse(dateStr, PATTERN_DATE);
	}
	
	/**
	 * 将字符串转换为日期，格式：yyyy-MM-dd HH:mm:ss
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date parseDateTime(String dateStr) {
		return parse(dateStr, PATTERN_DATE);
	}
	
	/**
	 * 将字符串转换为日期，格式：HH:mm:ss
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date parseTime(String dateStr) {
		return parse(dateStr, PATTERN_DATE);
	}
	
	/**
	 * 将字符串转换为日期
	 * @param dateStr 日期字符串
	 * @param pattern 格式字符串
	 * @return
	 */
	public static Timestamp parseTimestamp(String dateStr, String pattern) {
		Date date = parse(dateStr, pattern);
		return new Timestamp(date.getTime());
	}
	
	
	/**
	 * 取得日期格式转换器
	 * @return
	 */
	public static SimpleDateFormat getFormatter(String pattern) {
		SimpleDateFormat formatter = formatterMap.get(pattern);
		if (formatter == null) {
			synchronized (DateUtils.class) {
				if (formatterMap.get(pattern) == null) {
					formatter = new SimpleDateFormat(pattern);
					formatterMap.put(pattern, formatter);
				}
			}
		}
		return formatter;
	}
	
	/**
	 * 主要用于json的反序列化，日期的转换，会加入一定的容错处理
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date jsonDeserialize(String dateStr) {
		if (StringUtil.isNotBlank(dateStr)) {
			for (String pattern : jsonDeserializePatternArr) {
				try {
					SimpleDateFormat format = new SimpleDateFormat(pattern);
					Date date = format.parse(dateStr);
					return date;
				} catch (Exception e) {
					// 失败则继续下一个转换
				}
			}
		}

		return null;
	}
	
	/**
	 * Date加天数
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date dateAddDay(Date date, int day) {
		return dateAddDay(date, day, false);
	}

	/**
	 * Date加天数
	 * @param date
	 * @param day
	 * @param excludeTime 是否排除时间，去掉时分秒，只保留年月日
	 * @return
	 */
	public static Date dateAddDay(Date date, int day, boolean excludeTime) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 排除时间，去掉时分秒，只保留年月日
		if (excludeTime) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * 得到两个时间点相差的小时数(date2-date1)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getHour(Date date1, Date date2) {
		if (null == date1 || null == date2)
			return 0;
		return (date2.getTime() - date1.getTime()) / (1000 * 60 * 60);
	}
	
	/**
	 * Timestamp加天数
	 * 
	 * @param time
	 * @param day
	 * @return
	 */
	public static Timestamp timestampAddDay(Timestamp time, int day) {
		if (time == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * 大于（date1 > date2）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean gt(Date date1, Date date2) {
		return date1.compareTo(date2) > 0;
	}

	/**
	 * 大于等于（date1 >= date2）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean ge(Date date1, Date date2) {
		return !(date1.compareTo(date2) < 0);
	}

	/**
	 * 小于（date1 < date2）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean lt(Date date1, Date date2) {
		return date1.compareTo(date2) < 0;
	}

	/**
	 * 小于等于（date1 <= date2）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean le(Date date1, Date date2) {
		return !(date1.compareTo(date2) > 0);
	}

	/**
	 * 等于（date1 = date2）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean eq(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

}
