package com.ygxc.aqjy.common.utils;


import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

/**
 * 时间工具类
 * @author leiZheng  
 * @date 2019年6月17日
 */
public class DateUtil {

	/**
	 * 默认格式 yyyy
	 */
	public static String yearFormat = "yyyy";
	/**
	 * 默认格式 MM
	 */
	public static String monthFormat = "MM";
	/**
	 * 默认格式 yyyy-MM
	 */
	public static String yearMonthFormat = "yyyy-MM";

	/**
	 * 默认格式 yyyy-MM-dd
	 */
	public static String defaultFormat = "yyyy-MM-dd";

	/**
	 * 默认格式yyMMdd
	 */
	public static String yearMonthDay = "yyMMdd";
	/**
	 * 时分
	 */
	public static String hhmmFormat = "HH:mm";
	/**
	 * 默认格式yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String long_defaultFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * 默认格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String short_defaultFormat = "yyyy-MM-dd HH:mm:ss";

	/** 用于json的反序列化，日期的转换，UTC表达式列表 */
	public static final String[] jsonDeserializeUtcPatternArr = new String[] { "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
			"yyyy-MM-dd'T'HH:mm:ss'Z'" };

	/** 用于json的反序列化，日期的转换，表达式列表 */
	public static final String[] jsonDeserializePatternArr = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
			"yyyy-MM-dd" };

	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static LocalDateTime getCurrLocalDateTime() {
		return LocalDateTime.now();
	}
	
	/**
	 * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
	 * @param time
	 * @param number
	 * @param field
	 * @return
	 */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
                * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

}
