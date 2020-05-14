package com.ygxc.aqjy.common.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ygxc.aqjy.common.exception.YgxcBusinessException;



/**
 * 金额操作工具类
 * @author leiZheng  
 * @date 2018年7月23日
 */
public class NumberUtil {

	public NumberUtil() {

	}

	/**
	 * 封一进十
	 */
	public static final String TYPE_CEIL = "ceil";
	/**
	 * 去掉最后一位，不进
	 */
	public static final String TYPE_FLOOR = "floor";

	/**
	 * 保留几位小数，最后一位小数是进一位的
	 */
	public static double numSaveInDecimal(double num, int decimal) {
		double numNew = Math.ceil(num * Math.pow(10.0, decimal));
		numNew /= Math.pow(10.0, decimal);
		return numNew;
	}

	/**
	 * 保留几位小数
	 */
	public static double numSaveInDecimal(double num, int decimal, String type) {
		if (TYPE_FLOOR.equals(type)) {
			double numNew = Math.floor(num * Math.pow(10.0, decimal));
			numNew /= Math.pow(10.0, decimal);
			return numNew;
		}
		return numSaveInDecimal(num, decimal);
	}

	
	/**
	 * 四捨五入
	 * 
	 * @param num
	 * @param newScale
	 *            小數位
	 * @return
	 */
	public static BigDecimal setScale(BigDecimal num, Integer newScale) {
		return num == null ? null : num.setScale(newScale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 接近零的舍入:例如4.15，保留一位，4.1
	 * 
	 * @param num
	 * @param newScale
	 * @return
	 */
	public static BigDecimal setScaleByRoundDown(BigDecimal num, Integer newScale) {
		return num == null ? null : num.setScale(newScale, BigDecimal.ROUND_DOWN);
	}

	/**
	 * 加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.add(v2);
	}

	/**
	 * 加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal addBigDecimal(BigDecimal v1, BigDecimal v2) {
		if (v1 == null) {
			v1 = BigDecimal.ZERO;
		}
		if (v2 == null) {
			v2 = BigDecimal.ZERO;
		}
		return v1.add(v2);
	}

	/**
	 * 加
	 * 
	 * @param valArr
	 * @return
	 */
	public static BigDecimal addBigDecimal(BigDecimal... valArr) {
		BigDecimal result = BigDecimal.ZERO;

		if (valArr != null && valArr.length != 0) {
			for (BigDecimal val : valArr) {
				if (val == null) {
					val = BigDecimal.ZERO;
				}
				result = result.add(val);
			}
		}
		return result;
	}

	/**
	 * 加
	 * 
	 * @param valArr
	 * @return
	 */
	public static BigDecimal add(BigDecimal... valArr) {
		BigDecimal result = BigDecimal.ZERO;

		if (valArr != null && valArr.length != 0) {
			for (BigDecimal val : valArr) {
				if (val == null) {
					throw new YgxcBusinessException("Param not null!");
				}
				result = result.add(val);
			}
		}
		return result;
	}

	/**
	 * 加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal add(BigDecimal v1, int v2) {
		if (v1 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.add(new BigDecimal(v2));
	}

	/**
	 * 减
	 * 
	 * @param valArr
	 * @return
	 */
	public static BigDecimal sub(BigDecimal... valArr) {
		BigDecimal result = BigDecimal.ZERO;

		if (valArr != null && valArr.length != 0) {
			for (BigDecimal val : valArr) {
				if (val == null) {
					throw new YgxcBusinessException("Param not null!");
				}
				result = result.subtract(val);
			}
		}
		return result;
	}

	/**
	 * 减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.subtract(v2);
	}

	/**
	 * 減
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal subBigDecimal(BigDecimal v1, BigDecimal v2) {
		if (v1 == null) {
			v1 = BigDecimal.ZERO;
		}
		if (v2 == null) {
			v2 = BigDecimal.ZERO;
		}
		return v1.subtract(v2);
	}

	/**
	 * 減
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal subBigDecimal(BigDecimal... valArr) {
		BigDecimal result = BigDecimal.ZERO;

		if (valArr != null && valArr.length != 0) {
			for (BigDecimal val : valArr) {
				if (val == null) {
					val = BigDecimal.ZERO;
				}
				result = result.subtract(val);
			}
		}
		return result;
	}

	/**
	 * 乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.multiply(v2);
	}

	/**
	 * 乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal v1, int v2) {
		return mul(v1, new BigDecimal(v2));
	}

	/**
	 * 除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.divide(v2, 8, BigDecimal.ROUND_HALF_UP);
	}

	
	/**
	 * 除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal div(Integer v1, Integer v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return new  BigDecimal(v1).divide(new  BigDecimal(v2), 8, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal div(BigDecimal v1, int v2) {
		return div(v1, new BigDecimal(v2));
	}

	/**
	 * 大于
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean gt(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.compareTo(v2) > 0;
	}

	/**
	 * 大于等于
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean ge(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.compareTo(v2) >= 0;
	}

	/**
	 * 小于等于
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean le(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.compareTo(v2) <= 0;
	}

	/**
	 * 小于
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean lt(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.compareTo(v2) < 0;
	}

	/**
	 * 等于
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean eq(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null)
			throw new YgxcBusinessException("Param not null!");
		return v1.compareTo(v2) == 0;
	}

	/**
	 * 不等于
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean neq(BigDecimal v1, BigDecimal v2) {
		return !eq(v1, v2);
	}

	/**
	 * 判断并取出BigDecimal
	 * 
	 * <pre>
	 * null      =>   0
	 * val < 0   =>   0
	 * val >= 0  =>   val
	 * </pre>
	 * 
	 * @param val
	 * @return
	 */
	public static BigDecimal overZero(BigDecimal val) {
		if (val == null) {
			return BigDecimal.ZERO;
		}
		if (lt(val, BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}
		return val;
	}

	/**
	 * 默认BigDecimal
	 * 
	 * <pre>
	 * val == null      =>   0
	 * val != null      =>　　val
	 * </pre>
	 * 
	 * @param val
	 * @return
	 */
	public static BigDecimal defaultBigDecimal(BigDecimal val) {
		return val == null ? BigDecimal.ZERO : val;
	}

	/**
	 * <pre>
	 * 是否不等于0
	 * v = null      =>    false
	 * v = 0         =>    false
	 * v != 0        =>    true
	 * </pre>
	 * 
	 * @param v
	 * @return
	 */
	public static boolean neqZero(BigDecimal v) {
		if (v == null) {
			return false;
		}
		return BigDecimal.ZERO.compareTo(v) != 0;
	}

	/**
	 * <pre>
	 * 是否大于0
	 * v = null      =>    false
	 * v > 0         =>    true
	 * v <= 0        =>    false
	 * </pre>
	 * 
	 * @param v
	 * @return
	 */
	public static boolean gtZero(BigDecimal v) {
		if (v == null) {
			return false;
		}
		return v.compareTo(BigDecimal.ZERO) > 0;
	}

	/**
	 * <pre>
	 * 是否大于等于0
	 * v = null      =>    false
	 * v >= 0         =>    true
	 * v < 0        =>    false
	 * </pre>
	 * 
	 * @param v
	 * @return
	 */
	public static boolean geZero(BigDecimal v) {
		if (v == null) {
			return false;
		}
		return v.compareTo(BigDecimal.ZERO) >= 0;
	}



	/**
	 * 乘 ，保留2位小数，四舍五入
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal mulScaleFour(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null) {
			return v1;
		}
		return mul(v1, v2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除，保留n位小数，四舍五入
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal divScale(BigDecimal v1, BigDecimal v2, int scale) {
		if (v1 == null) {
			v1 = BigDecimal.ZERO;
		}
		if (v2 == null) {
			v2 = BigDecimal.ZERO;
		}

		if (eq(v2, BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		} else {
			return div(v1, v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
		}
	}

	/**
	 * 默认int
	 * 
	 * <pre>
	 * val == null      =>   0
	 * val != null      =>　　val
	 * </pre>
	 * 
	 * @param val
	 * @return
	 */
	public static Integer defaultInt(Integer val) {
		return val == null ? 0 : val;
	}

	/**
	 * 加
	 * 
	 * @param valArr
	 */
	public static Integer add(Integer... valArr) {
		int result = 0;
		for (Integer val : valArr) {
			result += defaultInt(val);
		}
		return result;
	}

	/**
	 * 加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal addTolerant(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null) {
			return v1;
		}
		return v1.add(v2);
	}

	/**
	 * 加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal addTolerant(BigDecimal... arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		if (arr[0] == null) {
			return null;
		}

		BigDecimal val = arr[0];
		if (arr.length > 1) {
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] != null) {
					val = val.add(arr[i]);
				}
			}
		}

		return val;
	}

	/**
	 * 减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal subTolerant(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null) {
			return v1;
		}
		return v1.subtract(v2);
	}

	/**
	 * 乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal mulTolerant(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null) {
			return v1;
		}
		return v1.multiply(v2);
	}

	/**
	 * 除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal divTolerant(BigDecimal v1, BigDecimal v2) {
		if (v1 == null || v2 == null) {
			return v1;
		}
		return v1.divide(v2, 8, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * String转Integer
	 * 
	 * @param val
	 */
	public static Integer strToInt(String val) {
		if (StringUtil.isBlank(val)) {
			return null;
		}
		return Integer.valueOf(val);
	}

	/***
	 * 取最小值
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal min(BigDecimal v1, BigDecimal v2) {
		if (v1 == null) {
			v1 = BigDecimal.ZERO;
		}
		if (v2 == null) {
			v2 = BigDecimal.ZERO;
		}
		return v1.min(v2);
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
		Matcher m = pattern.matcher(str);
		return m.matches();
	}

	/**
	 * 金额操作是否为最后一笔且有精度丢失，主要用于回补精度，规则：低于0.0005万则认为最后一笔
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLastOpForAmountPrecision(BigDecimal val) {
		if (val == null) {
			return false;
		}

		if (eq(val, BigDecimal.ZERO)) {
			return false;
		}
		if (le(val, new BigDecimal("0.0005")) && ge(val, new BigDecimal("-0.0005"))) {
			return true;
		}

		return false;
	}

	/**
	 * 计算订单锁库金额
	 * @param orderAmount 订单金额
	 * @param amountRate 锁库金额比例
	 * @param minAmount 最小金额
	 * @return
	 */
	public static BigDecimal calculateLockLibraryAmount(BigDecimal orderAmount, BigDecimal amountRate,
			BigDecimal minAmount) {
		BigDecimal  lockAmount = NumberUtil.mulScaleFour(orderAmount, amountRate);
		
		return NumberUtil.gt(minAmount, lockAmount)?minAmount:lockAmount;
	}

	/**
	 * 微信支付订单金额 回调  分转元
	 * @param totalFee
	 * @return
	 */
	public static BigDecimal convWxPayResultTotalFee(Integer totalFee) {
		BigDecimal wxPayResultTotalFee = new BigDecimal(totalFee).multiply(new BigDecimal("0.01"));
	                
		 return wxPayResultTotalFee;
	
	}
		
}
