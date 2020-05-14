package com.ygxc.aqjy.framework.redis.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import com.alibaba.fastjson.JSONObject;
import com.ygxc.aqjy.common.utils.ApplicationContextUtils;
import com.ygxc.aqjy.common.utils.Assert;


/**
 * redis工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RedisUtils {

	/**
	 * 取得redis模板类
	 * @return
	 */
	public static RedisTemplate<String, Object> getRedisTemplate() {
		ApplicationContext applicationContext = ApplicationContextUtils.getContext();
		Assert.notNull(applicationContext, "applicationContext is null");
		
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
		Assert.notNull(redisTemplate, "redisTemplate is null");
		return redisTemplate;
	}
	
	/**
	 * 取得opsForValue
	 * @return
	 */
	public static ValueOperations<String, Object> getOpsForValue() {
		return getRedisTemplate().opsForValue();
	}
	
	/**
	 * 取得opsForSet
	 * @return
	 */
	public static SetOperations<String, Object> getOpsForSet() {
		return getRedisTemplate().opsForSet();
	}
	
	/**
	 * 取得opsForList
	 * @return
	 */
	public static ListOperations<String, Object> getOpsForList() {
		return getRedisTemplate().opsForList();
	}
	
	/**
	 * 取得key列表
	 * @param keyPrefix
	 * @return
	 */
	public static Set<String> getKeys(String keyPrefix) {
		if (StringUtils.isBlank(keyPrefix)) {
			return null;
		}
		return getRedisTemplate().keys(keyPrefix);
	}
	
	/**
	 * 是否存在key
	 * @param key
	 * @return true：存在；false：不存在；
	 */
	public static boolean existKey(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		
		Set<String> keySet = getKeys(key);
		return CollectionUtils.isNotEmpty(keySet);
	}
	
	/**
	 * 保存到value
	 * @param key
	 * @param value
	 */
	public static void setToValue(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		if (value != null) {
			getOpsForValue().set(key, value);
		}
	}
	
	/**
	 * 保存到value
	 * @param key
	 * @param value
	 * @param seconds 失效时间，单位：秒
	 */
	public static void setToValue(String key, Object value, long seconds) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		if (value != null) {
			if (seconds > 0) {
				getOpsForValue().set(key, value, seconds, TimeUnit.SECONDS);
			} else {
				getOpsForValue().set(key, value);
			}
		}
	}
	
	/**
	 * 保存到value，json格式
	 * @param key
	 * @param value
	 */
	public static void setToValueJson(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		if (value != null) {
			String json = JSONObject.toJSONString(value);
			getOpsForValue().set(key, json);
		}
	}
	
	/**
	 * 保存到value，json格式
	 * @param key
	 * @param value
	 * @param seconds 失效时间，单位：秒
	 */
	public static void setToValueJson(String key, Object value, long seconds) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		if (value != null) {
			String json = JSONObject.toJSONString(value);
			
			if (seconds > 0) {
				getOpsForValue().set(key, json, seconds, TimeUnit.SECONDS);
			} else {
				getOpsForValue().set(key, json);
			}
		}
	}
	
	/**
	 * 从value取得数据
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getToValue(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		return (T) getOpsForValue().get(key);
	}
	
	/**
	 * 从value取得数据，string格式
	 * @param key
	 * @return
	 */
	public static String getToValueString(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		return (String) getOpsForValue().get(key);
	}
	
	/**
	 * 从value取得数据，Long格式
	 * @param key
	 * @return
	 */
	public static Integer getToValueInt(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		Object value = getOpsForValue().get(key);
		if (value == null) {
			return null;
		}
		return Integer.valueOf(value.toString());
	}

	/**
	 * 从value取得数据，Long格式
	 * @param key
	 * @return
	 */
	public static Long getToValueLong(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		Object value = getOpsForValue().get(key);
		if (value == null) {
			return null;
		}
		return Long.valueOf(value.toString());
	}

	/**
	 * 从value取得数据，json格式
	 * @param key
	 * @return
	 */
	public static <T> T getToValueJson(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		T obj = null;
		String json = getToValueString(key);
		if (StringUtils.isNotBlank(json)) {
			obj = JSONObject.parseObject(json, clazz);
		}
		return obj;
	}
	
	/**
	 * 从value取得一批数据，json格式
	 * @param key
	 * @return
	 */
	public static <T> List<T> getToValueBatchJson(String keyPrefix, Class<T> clazz) {
		if (StringUtils.isBlank(keyPrefix)) {
			return null;
		}
		
		List<T> list = new ArrayList<>();
		Set<String> keys = getKeys(keyPrefix);
		if (CollectionUtils.isNotEmpty(keys)) {
			for (String key : keys) {
				T obj = getToValueJson(key, clazz);
				if (obj != null) {
					list.add(obj);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 自增
	 * @param key
	 */
	public static void increment(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		getOpsForValue().increment(key, 1);
	}
	
	/**
	 * 自增
	 * @param key
	 */
	public static void increment(String key, long seconds) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		if (!existKey(key)) {
			setToValue(key, 0, seconds);
		}
		getOpsForValue().increment(key, 1);
	}
	
	/**
	 * 自减
	 * @param key
	 */
	public static void decrement(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		getOpsForValue().increment(key, -1);
	}
	
	/**
	 * 自减
	 * @param key
	 */
	public static void decrement(String key, long seconds) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		if (!existKey(key)) {
			setToValue(key, 0, seconds);
		}
		getOpsForValue().increment(key, -1);
	}
	
	/**
	 * 保存到set
	 * @param key
	 * @param value
	 */
	public static void setToSet(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			return;
		}

		getOpsForSet().add(key, value);
	}
	
	/**
	 * 保存到set
	 * @param key
	 * @param vals
	 */
	public static void setToSet(String key, Collection<?> coll) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		SetOperations<String, Object> op = getOpsForSet();
		if (CollectionUtils.isNotEmpty(coll)) {
			for (Object value : coll) {
				op.add(key, value);
			}
		}
	}
	
	/**
	 * 保存到set
	 * @param key
	 * @param vals
	 */
	public static void setToSetJson(String key, Collection<?> coll) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		SetOperations<String, Object> op = getOpsForSet();
		if (CollectionUtils.isNotEmpty(coll)) {
			for (Object value : coll) {
				String json = JSONObject.toJSONString(value);
				op.add(key, json);
			}
		}
	}

	/**
	 * 从set取得数据，String格式
	 * @param key
	 * @return
	 */
	public static Set<String> getToSet(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		SetOperations<String, Object> op = getOpsForSet();
		Set<Object> set = op.members(key);
		if (set == null) return null;
		return set.stream().map(Object::toString).collect(Collectors.toSet());
	}

	/**
	 * 从set删除数据
	 * @param key
	 * @param value
	 */
	public static void deleteToSet(String key, Object... value){
		if (StringUtils.isNotBlank(key) || value != null) {
			SetOperations<String, Object> op = getOpsForSet();
			op.remove(key, value);
		}
	}


	/**
	 * 从set取得数据，json格式
	 * @param <T>
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> Set<T> getToSetJson(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		SetOperations<String, Object> op = getOpsForSet();
		Set<Object> set = op.members(key);
		
		if (CollectionUtils.isEmpty(set)) {
			return null;
		}
		
		Set<T> entitySet = new HashSet<T>();
		for (Object obj : set) {
			if (obj == null) {
				continue;
			}
			
			T entity = JSONObject.parseObject(obj.toString(), clazz);
			entitySet.add(entity);
		}
		
		return entitySet;
	}
	
	/**
	 * 保存到list
	 * @param key
	 * @param value
	 */
	public static void setToList(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		getOpsForList().rightPush(key, value);
	}
	
	/**
	 * 保存到list
	 * @param key
	 * @param vals
	 */
	public static void setToList(String key, Collection<?> coll) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		ListOperations<String, Object> op = getOpsForList();
		if (CollectionUtils.isNotEmpty(coll)) {
			for (Object value : coll) {
				op.rightPush(key, value);
			}
		}
	}
	
	/**
	 * 保存到list
	 * @param key
	 * @param vals
	 */
	public static void setToListJson(String key, Collection<?> coll) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		ListOperations<String, Object> op = getOpsForList();
		if (CollectionUtils.isNotEmpty(coll)) {
			for (Object value : coll) {
				String json = JSONObject.toJSONString(value);
				op.rightPush(key, json);
			}
		}
	}
	
	/**
	 * 从list取得数据，json格式
	 * @param <T>
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> getToListJson(String key, Class<T> clazz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		ListOperations<String, Object> op = getOpsForList();
		List<Object> list = op.range(key, 0, -1);
		
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		List<T> entityList = new ArrayList<T>();
		for (Object obj : list) {
			if (obj == null) {
				continue;
			}
			
			T entity = JSONObject.parseObject(obj.toString(), clazz);
			entityList.add(entity);
		}
		return entityList;
	}

	/**
	 * 删除数据
	 * @param key String
	 */
	public static void delKey(String key){
		if (StringUtils.isBlank(key)) {
			return;
		}
		
		getRedisTemplate().delete(key);
	}
	
	/**
	 * 批量删除数据
	 * @param key String
	 */
	public static void delKey(Set<String> keys){
		if (CollectionUtils.isEmpty(keys)) {
			return;
		}
		
		getRedisTemplate().delete(keys);
	}
	
	/**
	 * 根据前缀批量删除key
	 * @param key
	 */
	public static void delKeyByPrefix(String keyPrefix){
		Set<String> keys = getKeys(keyPrefix);
		getRedisTemplate().delete(keys);
	}


}
