package com.ygxc.aqjy.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ygxc.aqjy.common.exception.YgxcBusinessException;



public class BeanUtils {
	
	/**
	 * 创建实例
	 * @param clazz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clazz) {
		if (clazz == null) {
			return null;
		}
		
		try {
			T obj = clazz.newInstance();
			return obj;
		} catch (Exception e) {
			throw new YgxcBusinessException("New instance error!");
		}
	}
	
	/**
	 * 拷贝对象
	 * @param dest 目标对象
	 * @param orig 源对象
	 */
	public static void copyProperties(Object dest, Object orig) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			throw new YgxcBusinessException("Copy properties error!", e);
		}
	}

	/**
	 * 将类转换为另一个类
	 * @param orig 源对象
	 * @param destClass 目标对象Class
	 * @return
	 */
	public static <T> T convertBean(Object orig, Class<T> destClass) {
		Assert.notNull(destClass, "Class can't be empty!");
		
		if (orig == null) {
			return null;
		}
		
		try {
			T dest = newInstance(destClass);
			copyProperties(dest, orig);
			return dest;
		} catch (Exception e) {
			throw new YgxcBusinessException("Convert bean error！", e);	
		}
	}
	
	/**
	 * 将类的List转换为另一个类的List
	 * @param origList 源对象List
	 * @param destClass 目标对象Class
	 */
	public static <T> List<T> convertBeanList(List<?> origList, Class<T> destClass) {
		Assert.notNull(destClass, "Class can't be empty!");
		
		if (origList == null) {
			return null;
		}
		
		List<T> destList = new ArrayList<>();
		
		if (origList.size() != 0) {
			for (Object orig : origList) {
				T dest = convertBean(orig, destClass);
				destList.add(dest);
			}
		}
		
		return destList;
	}
	
	/**
	 * 将类的Set转换为另一个类的Set
	 * @param origSet 源对象Set
	 * @param destClass 目标对象Class
	 */
	public static <T> Set<T> convertBeanSet(Set<?> origSet, Class<T> destClass) {
		Assert.notNull(destClass, "Class can't be empty!");
		
		if (origSet == null) {
			return null;
		}
		
		Set<T> destSet = new HashSet<>();
		
		if (origSet.size() != 0) {
			for (Object orig : origSet) {
				T dest = convertBean(orig, destClass);
				destSet.add(dest);
			}
		}
		
		return destSet;
	}
}
