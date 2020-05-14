package com.ygxc.aqjy.common.utils;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;


public class Assist {
	protected final static Logger logger = LoggerFactory.getLogger(Assist.class);

	/**
	 * 如果值存在则执行consumer（接受一个输入参数，无返回的操作），否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> void consumer(T obj, Consumer<T> action) {
		if (obj != null)
			action.accept(obj);
	}

	/**
	 * 如果值存在则执行function（接受一个输入参数，返回一个结果），否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param obj
	 * @param action
	 * @return
	 */
	public static <T, R> R function(T obj, Function<T, R> action) {
		if (obj != null)
			return action.apply(obj);
		return null;
	}

	/**
	 * 如果值存在则执行function（接受一个输入参数，返回一个结果），否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param obj
	 * @param function
	 * @return
	 */
	public static <R> R supplier(Object obj, Supplier<R> action) {
		if (obj != null)
			return action.get();
		return null;
	}

	/**
	 * 如果值存在则循环执行consumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param coll
	 * @param action
	 */
	public static <T> void forEach(Collection<T> coll, Consumer<T> action) {
		consumer(coll, o -> o.forEach(action));
	}

	/**
	 * 如果值存在则循环执行consumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param arr
	 * @param action
	 */
	public static <T> void forEach(T[] arr, Consumer<T> action) {
		consumer(arr, o -> Arrays.stream(arr).forEach(action));
	}

	/**
	 * 如果值存在则循环执行BiConsumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param arr
	 * @param action
	 */
	public static <K, V> void forEach(Map<K, V> map, BiConsumer<K, V> action) {
		consumer(map, o -> map.forEach(action));
	}

	/**
	 * 如果值存在则循环执行function转换为List，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T, R> List<R> forEachToList(Collection<T> coll, Function<T, R> action) {
		return coll == null ? new ArrayList<>() : coll.stream().map(action).collect(Collectors.toList());
	}

	/**
	 * 如果值存在则循环执行function转换为List，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T, R> List<R> forEachToList(T[] arr, Function<T, R> action) {
		return arr == null ? new ArrayList<>() : Arrays.stream(arr).map(action).collect(Collectors.toList());
	}

	/**
	 * 如果值存在则循环执行BiFunction转换为List，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <R, K, V> List<R> forEachToList(Map<K, V> map, BiFunction<K, V, R> action) {
		List<R> list = new ArrayList<>();
		forEach(map, (key, val) -> action.apply(key, val));
		return list;
	}

	/**
	 * 如果值存在则循环执行function转换为Set，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T, R> Set<R> forEachToSet(Collection<T> coll, Function<T, R> action) {
		return coll == null ? new HashSet<>() : coll.stream().map(action).collect(Collectors.toSet());
	}

	/**
	 * 如果值存在则循环执行function转换为Set，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T, R> Set<R> forEachToSet(T[] arr, Function<T, R> action) {
		return arr == null ? new HashSet<>() : Arrays.stream(arr).map(action).collect(Collectors.toSet());
	}

	/**
	 * 如果值存在则循环执行BiFunction转换为Set，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <R, K, V> Set<R> forEachToSet(Map<K, V> map, BiFunction<K, V, R> action) {
		Set<R> list = new HashSet<>();
		forEach(map, (key, val) -> action.apply(key, val));
		return list;
	}

	/**
	 * 如果值存在则循环转换为Map，否则不做任何事情
	 * 
	 * @param <T>
	 * @param <R>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T, K, V> Map<K, V> forEachToMap(Collection<T> coll, Function<T, K> keyAction,
			Function<T, V> valueAction) {
		return coll == null ? null : coll.stream().collect(Collectors.toMap(keyAction, valueAction));
	}

	/**
	 * 如果值存在则循环执行action，否则不做任何事情
	 * 
	 * @param <T>
	 * @param coll
	 * @param action
	 */
	public static <T> void forEachOrdered(List<T> coll, BiConsumer<Integer, T> action) {
		if (coll == null)
			return;
		for (int i = 0; i < coll.size(); i++)
			action.accept(i, coll.get(i));
	}

	/**
	 * 如果值存在则循环执行action，否则不做任何事情
	 * 
	 * @param <T>
	 * @param coll
	 * @param action
	 */
	public static <T> void forEachOrdered(T[] arr, BiConsumer<Integer, T> action) {
		if (arr == null)
			return;
		for (int i = 0; i < arr.length; i++)
			action.accept(i, arr[i]);
	}

	/**
	 * 如果值存在则循环执行action，否则不做任何事情
	 * 
	 * @param <T>
	 * @param coll
	 * @param action
	 */
	public static <T> void forEachOrdered(int[] arr, BiConsumer<Integer, Integer> action) {
		if (arr == null)
			return;
		for (int i = 0; i < arr.length; i++)
			action.accept(i, arr[i]);
	}

	/**
	 * 如果不为null，则执行consumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> void ifNotNull(T obj, Consumer<T> action) {
		if (obj != null)
			action.accept(obj);
	}

	/**
	 * 如果不为blank，则执行consumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static void ifNotBlank(String obj, Consumer<String> action) {
		if (isNotBlank(obj))
			action.accept(obj);
	}

	/**
	 * 如果不为empty，则执行consumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T, C extends Collection<T>> void ifNotEmpty(C obj, Consumer<C> action) {
		if (isNotEmpty(obj))
			action.accept(obj);
	}

	/**
	 * 如果不为empty，则执行consumer，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <K, V> void ifNotEmpty(Map<K, V> obj, Consumer<Map<K, V>> action) {
		if (isNotEmpty(obj))
			action.accept(obj);
	}

	/**
	 * 如果不为null，则执行function，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T, R> R ifNotNullFn(T obj, Function<T, R> action) {
		return obj == null ? null : action.apply(obj);
	}

	/**
	 * 如果不为blank，则执行function，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <R> R ifNotBlankFn(String obj, Function<String, R> action) {
		return isBlank(obj) ? null : action.apply(obj);
	}

	/**
	 * 如果不为blank，则执行function，否则返回默认值
	 * 
	 * @param obj
	 * @param action
	 */
	public static String ifNotBlankFnDefaultString(String obj, Function<String, String> action, String defaultStr) {
		return isBlank(obj) ? defaultStr : action.apply(obj);
	}

	/**
	 * 如果为true，则执行Supplier，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T ifTrue(boolean obj, Supplier<T> action) {
		return obj ? action.get() : null;
	}

	/**
	 * 如果为false，则执行Supplier，否则不做任何事情
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T ifFalse(boolean obj, Supplier<T> action) {
		return !obj ? action.get() : null;
	}

	/**
	 * 如果为true，则执行Supplier1，否则执行Supplier2
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T ifTrue(boolean obj, Supplier<T> actionOne, Supplier<T> actionTwo) {
		if (obj)
			return actionOne.get();
		else
			return actionTwo.get();
	}

	/**
	 * 如果为false，则执行Supplier1，否则执行Supplier2
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T ifFalse(boolean obj, Supplier<T> actionOne, Supplier<T> actionTwo) {
		if (!obj)
			return actionOne.get();
		else
			return actionTwo.get();
	}

	/**
	 * 如果为equals为true，则执行Supplier1，否则执行Supplier2
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T ifEqualsTrue(Object objOne, Object objTwo, Supplier<T> actionOne, Supplier<T> actionTwo) {
		if (objOne == null) {
			if (objTwo == null)
				return actionOne.get();
			else
				return actionTwo.get();
		}
		if (objTwo == null)
			return actionTwo.get();

		if (objOne.equals(objTwo))
			return actionOne.get();
		else
			return actionTwo.get();
	}

	/**
	 * 如果为equals为true，则执行action，否则不做任何事
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T ifEqualsTrue(Object objOne, Object objTwo, Supplier<T> action) {
		if (objOne == null) {
			if (objTwo == null)
				return action.get();
		}

		if (objOne.equals(objTwo))
			return action.get();

		return null;
	}

	/**
	 * 如果为null，则不做任何事；如果不为null，如果为String则执行actionOne，否则执行actionTwo，
	 * 
	 * @param <T>
	 * @param <R>
	 * @param obj
	 * @param actionOne
	 * @param actionTwo
	 * @return
	 */
	public static <T, R> R ifIsString(T obj, Function<String, R> actionOne, Function<T, R> actionTwo) {
		if (obj == null)
			return null;
		if (obj instanceof String)
			return actionOne.apply((String) obj);
		else
			return actionTwo.apply(obj);
	}

	/**
	 * 如果为null，则不做任何事；如果不为null，如果为String则执行直接返回，否则执行action得到新String并返回
	 * 
	 * @param <T>
	 * @param <R>
	 * @param obj
	 * @param actionOne
	 * @param actionTwo
	 * @return
	 */
	public static <T> String ifIsStringTo(T obj, Function<T, String> action) {
		if (obj == null)
			return null;
		if (obj instanceof String)
			return (String) obj;
		else
			return action.apply(obj);
	}

	/**
	 * 是否为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<?> coll) {
		return coll == null || coll.size() == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * 是否为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isBlank(String val) {
		return StringUtils.isBlank(val);
	}

	/**
	 * 是否为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isBlank(Object val) {
		return StringUtil.isBlank(val);
	}

	/**
	 * 是否不为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isNotBlank(String val) {
		return !isBlank(val);
	}

	/**
	 * 是否不为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isNotBlank(Object val) {
		return !isBlank(val);
	}

	/**
	 * 是否为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isEmpty(T[] val) {
		return val == null || val.length == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * @param val
	 * @return
	 */
	public static <T> boolean isNotEmpty(T[] val) {
		return !isEmpty(val);
	}

	/**
	 * 是否为空
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> val) {
		return val == null || val.size() == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> val) {
		return !isEmpty(val);
	}

	/**
	 * <pre>
	 * 默认字符串
	 * "a" => "a"
	 * "  " => defaultStr
	 * null => defaultStr
	 * </pre>
	 * 
	 * @param str
	 */
	public static String defaultString(String str, String defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}

	/**
	 * 默认字符串
	 * 
	 * @param val
	 * @return
	 */
	public static String defaultString(Object val) {
		return val == null ? BConst.EMPTY : val.toString();
	}

	/**
	 * <pre>
	 * 默认字符串，并用最终值执行action
	 * "a" => "a"
	 * "  " => defaultStr
	 * null => defaultStr
	 * </pre>
	 * 
	 * @param str
	 */
	public static String defaultString(String str, String defaultStr, Consumer<String> action) {
		String finalStr = defaultString(str, defaultStr);
		action.accept(finalStr);
		return finalStr;
	}

	/**
	 * <pre>
	 * 默认字符串
	 * "a" => "a"
	 * "  " => ""
	 * null => ""
	 * </pre>
	 * 
	 * @param str
	 */
	public static String defaultBlank(String str) {
		return defaultString(str, BConst.EMPTY);
	}

	/**
	 * <pre>
	 * 默认字符串
	 * "a" => "a"
	 * "  " => ""
	 * null => ""
	 * </pre>
	 * 
	 * @param str
	 */
	public static void defaultBlank(String str, Consumer<String> action) {
		action.accept(defaultBlank(str));
	}

	/**
	 * 默认值
	 * 
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static <T> T defaultVal(T val, T defaultVal) {
		return val == null ? defaultVal : val;
	}

	/**
	 * 默认值，并用最终值执行action
	 * 
	 * @param <T>
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static <T> void defaultVal(T val, T defaultVal, Consumer<T> action) {
		T finalVal = defaultVal(val, defaultVal);
		action.accept(finalVal);
	}

	/**
	 * 默认List，null => new ArrayList<>()
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> defaultList(List<T> list) {
		return list == null ? new ArrayList<T>() : list;
	}

	/**
	 * 默认Set，null => new HashSet<>()
	 * 
	 * @param list
	 * @return
	 */
	public static <T> Set<T> defaultSet(Set<T> list) {
		return list == null ? new HashSet<T>() : list;
	}

	/**
	 * 默认Map，null => new HashMap<>()
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> Map<K, V> defaultMap(Map<K, V> map) {
		return map == null ? new HashMap<>() : map;
	}

	/**
	 * 默认值true
	 * 
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static boolean defaultTrue(Boolean val) {
		return val == null ? true : val;
	}

	/**
	 * 默认值false
	 * 
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static boolean defaultFalse(Boolean val) {
		return val == null ? false : val;
	}

	/**
	 * 不能为null
	 * 
	 * @param <T>
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static <T> T notNull(T obj) {
		return notNull(obj, "The argument must not be null");
	}

	/**
	 * 不能为null
	 * 
	 * @param <T>
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static <T> T notNull(T obj, String msg) {
		if (obj == null)
			throw new YgxcBusinessException(msg);
		return obj;
	}

	/**
	 * 不能为null
	 * 
	 * @param <T>
	 * @param obj
	 * @return
	 */
	public static <T> T notNull(T obj, Consumer<T> action) {
		notNull(obj);
		action.accept(obj);
		return obj;
	}

	/**
	 * 不能为null
	 * 
	 * @param <T>
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static <T> T notNull(T obj, Consumer<T> action, String msg) {
		notNull(obj, msg);
		action.accept(obj);
		return obj;
	}

	/**
	 * 不能为null
	 * 
	 * @param <T>
	 * @param <R>
	 * @param obj
	 * @param msg
	 * @param action
	 * @return
	 */
	public static <T, R> R notNullFn(T obj, Function<T, R> action) {
		notNull(obj);
		return action.apply(obj);
	}

	/**
	 * 不能为null
	 * 
	 * @param <T>
	 * @param <R>
	 * @param obj
	 * @param msg
	 * @param action
	 * @return
	 */
	public static <T, R> R notNullFn(T obj, Function<T, R> action, String msg) {
		notNull(obj, msg);
		return action.apply(obj);
	}

	/**
	 * 不能为blank
	 * 
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static String notBlank(String obj) {
		return notBlank(obj, "The argument must not be blank");
	}

	/**
	 * 不能为blank
	 * 
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static String notBlank(String obj, String msg) {
		if (isBlank(obj))
			throw new YgxcBusinessException(msg);
		return obj;
	}

	/**
	 * 不能为blank
	 * 
	 * @param obj
	 * @return
	 */
	public static String notBlank(String obj, Consumer<String> action) {
		notBlank(obj);
		action.accept(obj);
		return obj;
	}

	/**
	 * 不能为blank
	 * 
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static String notBlank(String obj, Consumer<String> action, String msg) {
		notBlank(obj, msg);
		action.accept(obj);
		return obj;
	}

	/**
	 * 不能为blank
	 * 
	 * @param <R>
	 * @param obj
	 * @param msg
	 * @param action
	 * @return
	 */
	public static <R> R notBlankFn(String obj, Function<String, R> action) {
		notBlank(obj);
		return action.apply(obj);
	}

	/**
	 * 不能为blank
	 * 
	 * @param <R>
	 * @param obj
	 * @param msg
	 * @param action
	 * @return
	 */
	public static <R> R notBlankFn(String obj, Function<String, R> action, String msg) {
		notBlank(obj, msg);
		return action.apply(obj);
	}

	/**
	 * 不能为empty
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T extends Collection<?>> T notEmpty(T obj, String msg) {
		if (isEmpty(obj))
			throw new YgxcBusinessException(msg);
		return obj;
	}

	/**
	 * 不能为empty
	 * 
	 * @param <T>
	 * @param obj
	 * @param action
	 */
	public static <T> T[] notEmpty(T[] obj, String msg) {
		if (isEmpty(obj))
			throw new YgxcBusinessException(msg);
		return obj;
	}

	/**
	 * 不能相等
	 * 
	 * @param <T>
	 * @param o1
	 * @param o2
	 * @param msg
	 */
	public static <T> void notEquals(T o1, T o2, String msg) {
		if (o1 == null)
			return;
		if (o2 == null)
			return;
		if (o1.equals(o2))
			throw new YgxcBusinessException(msg);
	}

	/**
	 * 必须为null
	 * 
	 * @param <T>
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static <T> T mustNull(T obj, String msg) {
		if (obj != null)
			throw new YgxcBusinessException(msg);
		return obj;
	}

	/**
	 * 必须为blank
	 * 
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static String mustBlank(String obj, String msg) {
		if (isNotBlank(obj))
			throw new YgxcBusinessException(msg);
		return obj;
	}

	/**
	 * 必须相等
	 * 
	 * @param <T>
	 * @param o1
	 * @param o2
	 * @param msg
	 */
	public static <T> void mustEquals(T o1, T o2, String msg) {
		if (o1 == null)
			return;
		if (o2 == null)
			return;
		if (!o1.equals(o2))
			throw new YgxcBusinessException(msg);
	}

	/**
	 * 必须为true
	 * 
	 * @param <T>
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static void mustTrue(boolean obj, String msg) {
		if (!obj)
			throw new YgxcBusinessException(msg);
	}

	/**
	 * 必须为false
	 * 
	 * @param <T>
	 * @param obj
	 * @param msg
	 * @return
	 */
	public static void mustFalse(boolean obj, String msg) {
		if (obj)
			throw new YgxcBusinessException(msg);
	}

	/**
	 * 转String
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	/**
	 * 转List
	 * 
	 * @param <T>
	 * @param arr
	 * @return
	 */
	public static <T> List<T> toList(T[] arr) {
		return ifNotNullFn(arr, o -> Arrays.stream(o).collect(Collectors.toList()));
	}

	/**
	 * 转List
	 * 
	 * @param <T>
	 * @param coll
	 * @return
	 */
	public static <T> List<T> toList(Collection<T> coll) {
		return ifNotNullFn(coll, o -> o.stream().collect(Collectors.toList()));
	}

	/**
	 * 转Integer
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer toInteger(Object obj) {
		return obj == null ? null : Integer.valueOf(obj.toString());
	}

	/**
	 * 转Integer
	 * 
	 * @param obj
	 * @return
	 */
	public static Long toLong(Object obj) {
		return obj == null ? null : Long.valueOf(obj.toString());
	}

	/**
	 * <pre>
	 * 转json
	 * null => "{}"
	 * "abc" => "abc"
	 * 对象 => JSONObject.toJSONString(对象)
	 * </pre>
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		if (obj == null)
			return "{}";
		if (obj instanceof String)
			return (String) obj;
		return JSONObject.toJSONString(obj);
	}

	/**
	 * 转换为树列表
	 * 
	 * @param <T>
	 * @param <I>
	 * @param coll
	 * @param idAction
	 * @param parentIdAction
	 * @param addChildAction
	 * @return
	 */
	public static <T, I> List<T> toTree(Collection<T> coll, Function<T, I> idAction, Function<T, I> parentIdAction,
			BiConsumer<T, T> addChildAction) {
		if (coll == null)
			return null;
		List<T> treeList = new ArrayList<>();
		Map<I, T> map = forEachToMap(coll, idAction, obj -> obj);
		coll.forEach(obj -> {
			// 父ID
			I parentId = parentIdAction.apply(obj);
			// 为空则为根节点
			if (isBlank(parentId)) {
				treeList.add(obj);
				return;
			}

			// 父节点
			T parentObj = map.get(parentId);
			notNull(parentObj, "parentId \"" + parentId + "\" not found");
			// 添加子节点
			addChildAction.accept(parentObj, obj);
		});
		return treeList;
	}

	/**
	 * 加入元素
	 * 
	 * @param <T>
	 * @param coll
	 * @param arr
	 */
	@SafeVarargs
	public static <T> void add(Collection<T> coll, T... arr) {
		if (coll == null)
			return;
		forEach(arr, coll::add);
	}

	/**
	 * 加入元素
	 * 
	 * @param <T>
	 * @param coll
	 * @param arr
	 */
	public static <T> void add(Collection<T> coll, Collection<T> coll2) {
		if (coll == null)
			return;
		forEach(coll2, coll::add);
	}

	/**
	 * 如果不为null，则过滤并转List，否则不做任何事情
	 * 
	 * @param <T>
	 * @param coll
	 * @param predicate
	 * @return
	 */
	public static <T> List<T> filterToList(Collection<T> coll, Predicate<T> predicate) {
		if (coll == null)
			return null;
		return coll.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * 如果不为null，则过滤并取唯一（第一条）记录，否则不做任何事情
	 * 
	 * @param <T>
	 * @param coll
	 * @param predicate
	 * @return
	 */
	public static <T> T filterSingle(Collection<T> coll, Predicate<T> predicate) {
		if (coll == null)
			return null;
		return coll.stream().filter(predicate).findFirst().orElse(null);
	}

	/**
	 * trim字符串，并最后返回List
	 * 
	 * @param coll
	 * @return
	 */
	public static List<String> trimToList(Collection<String> coll) {
		return ifNotNullFn(coll, o -> o.stream().map(String::trim).collect(Collectors.toList()));
	}

	/**
	 * 集合中是否有匹配上
	 * 
	 * @param <T>
	 * @param coll
	 * @param action
	 * @return
	 */
	public static <T> boolean anyMatch(Collection<T> coll, Predicate<T> action) {
		if (isEmpty(coll))
			return false;
		return coll.stream().anyMatch(action);
	}

	/**
	 * 取得map的key列表
	 * 
	 * @param <T>
	 * @param map
	 * @return
	 */
	public static <T> List<T> keyList(Map<T, ?> map) {
		return Assist.forEachToList(map, (key, value) -> {
			return key;
		});
	}

	/**
	 * 资源释放
	 * 
	 * @param sources
	 */
	public static void close(Closeable... sources) {
		Assist.forEach(sources, source -> {
			try {
				source.close();
			} catch (Exception e) {
				logger.error("source close error!", e);
			}
		});
	}

	/**
	 * 拼接字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String concat(Object... arr) {
		StringBuilder sb = new StringBuilder();
		forEach(arr, obj -> sb.append(defaultString(obj)));
		return sb.toString();
	}

	/**
	 * 顺序匹配值，并返回result
	 */
	@SuppressWarnings("unchecked")
	public static <T, R> R orderCompare(T obj, Object... args) {
		if (obj == null)
			return null;
		int size = args.length / 2;
		for (int i = 0; i < size; i++) {
			T val = (T) args[i * 2];
			R result = (R) args[i * 2 + 1];
			if (obj.equals(val))
				return result;
		}
		return null;
	}

	/**
	 * 顺序匹配值，执行action，并返回result
	 */
	public static <T, R> R orderCompare(T obj, Consumer<R> action, Object... args) {
		R result = orderCompare(obj, args);
		if (result != null)
			action.accept(result);
		return result;
	}

//	/**
//	 * 顺序匹配值，并返回result
//	 */
//	public static <T, R> R orderCompare(T obj, T val1, R result1, T val2, R result2) {
//		if (obj == null) return null;
//		if (obj.equals(val1))
//			return result1;
//		else if (obj.equals(val2))
//			return result2;
//		return null;
//	}
//	
//	/**
//	 * 顺序匹配值，执行action，并返回result
//	 */
//	public static <T, R> R orderCompare(T obj, T val1, R result1, T val2, R result2, Consumer<R> action) {
//		R result = orderCompare(obj, val1, result1, val2, result2);
//		if (result != null) action.accept(result);
//		return result;
//	}
}
