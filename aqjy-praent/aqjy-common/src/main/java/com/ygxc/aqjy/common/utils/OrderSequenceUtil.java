package com.ygxc.aqjy.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单序列工具
 * @author leiZheng  
 * @date 2019年9月10日
 */
public class OrderSequenceUtil {
	/**
	 * 获取订单序列
	 * 
	 * @param result
	 * @return
	 */
	public static String getOrderCodeSequence() {
		String  prefix = "NTY", dateStr, randomStr;
		dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		randomStr = new Integer(((int) ((Math.random() * 9 + 1) * 1000))).toString();
		return prefix + dateStr + randomStr;
	}

	/**
	 * 获取退货序列
	 * 
	 * @param result
	 * @return
	 */
	public static String getReturnGoodsCodeSequence() {
		String prefix = "SH", dateStr, randomStr;
		dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		randomStr = new Integer(((int) ((Math.random() * 9 + 1) * 1000))).toString();
		return prefix + dateStr + randomStr;
	}

}
