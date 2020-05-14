package com.ygxc.aqjy.common.structure;

import java.util.HashMap;
import java.util.Map;

import com.ygxc.aqjy.common.utils.Assert;


public class RequestCommParamsHolder {
	/** 公共请求参数当前线程值 */
	private static final InheritableThreadLocal<Map<String, Object>> holder = new InheritableThreadLocal<>();
	
	/**
	 * 设置公共请求参数
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value) {
		Assert.notBlank(key, "key cannot be blank");
		
		Map<String, Object> map = holder.get();
		if (map == null) {
			map = new HashMap<>();
			holder.set(map);
		}
		map.put(key, value);
	}
	
	/**
	 * 取得公共请求参数
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Assert.notBlank(key, "key cannot be blank");
		
		Map<String, Object> map = holder.get();
		if (map == null) {
			return null;
		}
		
		return map.get(key);
	}
	
	/**
	 * 取得公共请求参数，字符串
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Object value = get(key);
		if (value == null) {
			return null;
		}
		
		return value.toString();
	}
}
