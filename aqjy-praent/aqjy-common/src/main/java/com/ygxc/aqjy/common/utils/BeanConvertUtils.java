package com.ygxc.aqjy.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ygxc.aqjy.common.exception.YgxcBusinessException;

/**
 *  类与类相互转换的工具类
 * @author leiZheng  
 * @date 2019年6月17日
 */
public class BeanConvertUtils {
	
	/**
	 * 将类转换为另一个类
	 * @param from
	 * @param toClass
	 * @return
	 */
	public static <T> T convertBean(Object from, Class<T> toClass) {
		if (toClass == null)
			throw new YgxcBusinessException("Class can't be empty!");
		
		if (from == null)
			return null;
		
		try {
			T to = toClass.newInstance();
			BeanCopyUtil.copyProperties(to, from);
			return to;
		} catch (Exception e) {
			throw new YgxcBusinessException("Convert bean error！", e);
		}
	}
	
	/**
	 * 将一个类的list转换为另一个类的list
	 * @param fromList
	 * @param toClass
	 * @return
	 */
	public static <T> List<T> convertBeanList(List<?> fromList, Class<T> toClass) {
		if (toClass == null)
			throw new YgxcBusinessException("Class can't be empty!");
		
		List<T> toList = new ArrayList<T>();
		
		if (CollectionUtil.isNotEmpty(fromList)) {
			for (Object from : fromList)
				toList.add(convertBean(from, toClass));
		}
		return toList;
	}
	
	/**
	 * 将一个类的set转换为另一个类的set
	 * @param fromSet
	 * @param toClass
	 * @return
	 */
	public static <T> Set<T> convertBeanSet(Set<?> fromSet, Class<T> toClass) {
		if (toClass == null)
			throw new YgxcBusinessException("Class can't be empty!");
		
		Set<T> toList = new HashSet<T>();
		if (fromSet != null && !fromSet.isEmpty()) {
			for (Object from : fromSet)
				toList.add(convertBean(from, toClass));
		}
		return toList;
	}

	/**
	 * 初始化實體 BigDecimal 值為零
	 * @param obj
     */
	public static void initBigDecimal(Object obj) {
		try {
			Method[] methods  = obj.getClass().getMethods();
			for(Method m : methods){
				if(m.getName().startsWith("set")){
					Class[] types = m.getParameterTypes();
					if(types.length == 1 && "java.math.BigDecimal".equals(types[0].getName())){
						m.invoke(obj, BigDecimal.ZERO);
					}
				}
			}
		} catch (Exception e) {
			//不可能出的錯誤
			throw new YgxcBusinessException(e.getMessage());
		}
	}
	
	/****/	
	public static Object convertMapToBean(Map<String, Object> data, Class<?> clazz)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		/***/
		Set<String> excludeFields = new HashSet<String>() {{
			add("serialVersionUID");
		}};
		/***/
		Object targetBean = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) { // 遍历所有属性
			/***/
			String name = field.getName(); // 获取属性的名字
			String setName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Class<?> type = field.getType(); // 获取属性的类型
			/***/
			if(excludeFields.contains(name)) {
				continue;
			}
			/***/
			field.setAccessible(true);
			Method method = clazz.getMethod(setName, type);
			if (type.equals(java.util.List.class)) {
				// 如果是List类型，得到其Generic的类型
				Type genericType = field.getGenericType();
				if (genericType == null) {
					continue;
				}
				// 如果是泛型参数的类型
				if (genericType instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) genericType;
					// 得到泛型里的class类型对象
					Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
					Object targetBeanList = convertMaptoBeanForList((List<Map<String, Object>>) data.get(name),
							genericClazz);					
					method.invoke(targetBean, targetBeanList);
				}
			} else if (type.equals(String.class)) {				
				method.invoke(targetBean, StringUtil.defaultString(data.get(name)));
			}else if(type.equals(Integer.class)) {
				method.invoke(targetBean, Integer.parseInt(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(java.math.BigDecimal.class)) {
				method.invoke(targetBean, new BigDecimal(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(Long.class)) {
				method.invoke(targetBean, Long.parseLong(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(Short.class)) {
				method.invoke(targetBean, Short.parseShort(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(Byte.class)) {
				method.invoke(targetBean, Byte.parseByte(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(Float.class)) {
				method.invoke(targetBean, Float.parseFloat(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(Double.class)) {
				method.invoke(targetBean, Double.parseDouble(StringUtil.defaultString(data.get(name))));
			}else if(type.equals(Character.class)) {
				method.invoke(targetBean, StringUtil.defaultString(data.get(name)).charAt(0));
			}else if(type.equals(Boolean.class)) {
				method.invoke(targetBean,Boolean.parseBoolean(StringUtil.defaultString(data.get(name))));
			}
			/***/
		}
		return targetBean;
	}

	public static List<Object> convertMaptoBeanForList(List<Map<String, Object>> dataList, Class<?> clazz)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {
		List<Object> targetBeanList = new ArrayList<>();
		for (Map<String, Object> data : dataList) {
			Object targetBean = convertMapToBean(data, clazz);
			targetBeanList.add(targetBean);
		}
		return targetBeanList;
	}

	/****/
	public static <T> Map<String, Object> convertBeanToMap(T t){
		return JsonUtil.toBean(JsonUtil.toJSONString(t),new HashMap<String, Object>().getClass());
	}
}
