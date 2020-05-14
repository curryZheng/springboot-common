package com.ygxc.aqjy.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 字符串工具类
 * @author leiZheng  
 * @date 2019年6月17日
 */
public class StringUtil extends StringUtils {

	/**
	 * 默认分隔符英文逗号 ,
	 */
	public static final String defaultSeparator = ",";

	/**
	 * 验证字符串是否有效 空为无效
	 * 
	 * @param str
	 * @return 有效为ture 无效为false
	 */
	public static boolean isValidString(String str) {
		return null != str && 0 < str.trim().length();
	}
	
	/**
	 * 是否为null或空字符串
	 * @param val
	 * @return
	 */
	public static boolean isBlank(Object val) {
		if (val == null) {
			return true;
		} else if(val instanceof String) {
			return isBlank(val.toString());
		}
		
		return false;
	}

	/**
	 * 将集合中的字符串按照指定分隔符划分返回字符串
	 * 
	 * @param list
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static String listToString(List<String> list, String separator) {
		if (!isValidString(separator))
			separator = defaultSeparator;
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			sb.append(str).append(separator);
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1); // 去除最后的分隔符
		}
		return sb.toString();
	}

	/**
	 * 以{}占位符来替换字符串
	 * @param str
	 * @param args
	 * @return
	 */
	public static String replaceBrace(String str, Object...args) {
		if (isNotBlank(str)) {
			for (Object arg : args) {
				str = str.replaceFirst("{}", defaultString(arg));
			}
		}
		
		return str;
	}
	
	/**
	 * 将集合中的字符串按照指定分隔符划分返回字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List<Long> list) {
		StringBuilder sb = new StringBuilder();
		for (Long str : list) {
			sb.append(str).append(defaultSeparator);
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1); // 去除最后的分隔符
		}
		return sb.toString();
	}

	/**
	 * 将list转为String 格式如： 'a','b','c','d'
	 * 
	 * @param sqlList
	 * @return
	 */
	public static String listToSQLString(Collection<String> sqlList) {
		if (null != sqlList && sqlList.size() > 0) {
			StringBuffer idLst = new StringBuffer("'");
			for (String obj : sqlList) {
				idLst.append(obj).append("','");
			}
			if (idLst.length() > 0) {
				idLst = idLst.deleteCharAt(idLst.length() - 1);
				idLst = idLst.deleteCharAt(idLst.length() - 1);
			}
			return idLst.toString();
		}
		return null;
	}

	/**
	 * 将string转换为List<Long>
	 * 
	 * @param str
	 * @return
	 */
	public static List<Long> stringToLongList(String str) {
		List<Long> ids = new ArrayList<Long>();
		String[] idsStr = str.split(defaultSeparator);
		for (String idStr : idsStr) {
			ids.add(Long.parseLong(idStr));
		}
		return ids;
	}

	/**
	 * 将string转换为List<String>
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> stringToList(String str, String separator) {
		if (!isValidString(str))
			return Collections.EMPTY_LIST;
		if (!isValidString(separator)) {
			separator = defaultSeparator;
		}
		List<String> list = new ArrayList<String>();
		String[] strArray = str.split(separator);
		for (String s : strArray) {
			list.add(s);
		}
		return list;
	}

	/**
	 * 
	 * @param value
	 */
	public static String htmlEncode(String value) {
		if (value == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			case '&':
				buffer.append("&amp;");
				break;
			case '"':
				buffer.append("&quot;");
				break;
			case 10:
				break;
			case 13:
				break;
			default:
				buffer.append(c);
			}
		}
		value = buffer.toString();
		return value;
	}

	/**
	 * 用于oracle用in语句查询时，将list参数分组 1组输出格式：1,2,3,4 大于1组，输出格式 ：1,2) or in (3,4
	 * 
	 * @param ids
	 * @param count
	 *            每组的个数 最大1000
	 * @return
	 */
	public static String getOracleSQLIn(List<Long> ids, int count, String filed) {
		count = Math.min(count, 1000);
		int len = ids.size();
		int size = len % count;
		if (size == 0) {
			size = len / count;
		} else {
			size = (len / count) + 1;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int fromIndex = i * count;
			int toIndex = Math.min(fromIndex + count, len);
			List<Long> tempList = ids.subList(fromIndex, toIndex);
			for (Long idStr : tempList) {
				sb.append(idStr).append(",");
			}
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 1); // 去除最后的分隔符
			}
			if (i == 0 && (size - 1) > 0) {
				sb.append(")");
			}
			if (i != 0 && i != (size - 1)) {
				sb.append(")");
			}
			if (i == 0 && (size - 1) > 0) {
				sb.append(" or " + filed + " in (");
			}
			if (i != 0 && i != (size - 1)) {
				sb.append(" or " + filed + " in (");
			}
		}
		return sb.toString();
	}

	/**
	 * 是否有一个字符串不为空，只有有一个就为true
	 * 
	 * @param strArr
	 * @return
	 */
	public static boolean isOneNotBlank(CharSequence... strArr) {
		if (strArr == null || strArr.length == 0)
			return false;
		for (CharSequence str : strArr) {
			if (isNotBlank(str))
				return true;
		}
		return false;
	}

	/**
	 * 是否有一个字符串为空，只要有一个就为true
	 * 
	 * @param strArr
	 * @return
	 */
	public static boolean isOneBlank(CharSequence... strArr) {
		if (strArr == null || strArr.length == 0)
			return true;
		for (CharSequence str : strArr) {
			if (isBlank(str))
				return true;
		}
		return false;
	}

	
	/**
	 * 拼接字符串，排除null
	 * 
	 * @param strArr
	 * @return
	 */
	public static String joinExcludeNull(String... strArr) {
		StringBuilder sb = new StringBuilder();
		for (String str : strArr) {
			if (str != null) {
				sb.append(str);
			}
		}
		return sb.toString();
	}

	/**
	 * 默认字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String defaultString(Object obj) {
		return obj == null ? EMPTY : obj.toString();
	}

	/**
	 * null返回空字符串，否则返回对象本身
	 * 
	 * @param obj
	 * @return
	 */
	public static Object defaultObject(Object obj) {
		return obj == null ? EMPTY : obj;
	}

	/**
	 * 递增序列号，需要考虑001，转换为数字时会丢失0的问题
	 * 
	 * @param currindex
	 * @param increase
	 */
	public static String orgSequenceProgressIndex(String currindex, Integer increase) {
		int num = currindex.length();
		
		BigDecimal useIncrease = BigDecimal.ONE;
		if (increase != null) {
			useIncrease = new BigDecimal(increase);
		}
		
		BigDecimal newIndex = new BigDecimal(currindex).add(useIncrease);
		String newIndexStr = newIndex.toString();
		
		int newNum = newIndexStr.length();
		
		//生成的位数比原本位数，则补0
		if (newNum < num) {
			int addCount = num - newNum;
			for (int i = 0; i < addCount; i++) {
				newIndexStr = "0" + newIndexStr;
			}
		}
		
		return newIndexStr;
	}


}
