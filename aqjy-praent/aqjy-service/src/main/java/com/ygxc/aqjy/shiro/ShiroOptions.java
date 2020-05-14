package com.ygxc.aqjy.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;

import com.ygxc.aqjy.shiro.manager.ShiroSessionManager;

/**
 * shiro配置项
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroOptions {
	
	/** 是否启用开发模式 */
	public static boolean DEV = false;
	
	/** 是否开启权限验证 */
	public static boolean DISABLED_PERMIT = false;
	
	/** shiro session id在servletRequest header中的字段名 */
	public static String SESSION_ID_FIELD_NAME = "token";
	
	/** 加密算法方式 */
	public static String ENCRYPT_ALGORITHM_NAME = Md5Hash.ALGORITHM_NAME;
	
	/** 加密次数 */
	public static int ENCRYPT_HASH_ITERATIONS = 1024;
	
	/** SessionManager Class */
	public static Class<? extends DefaultSessionManager> SESSION_MANAGER_CLASS = ShiroSessionManager.class;
	
	/** 权限映射 */
	public static Map<String, String> FILTER_CHAIN_DEFINITION_MAP = new LinkedHashMap<>();
	
	/** 未登录消息，json格式 */
	public static String UNAUTHORIZED_MSG = "{}";
	
	/** 无权限访问消息，json格式 */
	public static String UNPERMIT_MSG = "{}";
	
	/**
	 * <pre>
	 * 是否开启密码二次动态加密
	 * 在原有加密完后的密码进行二次动态加密，目前参考spring-security的加密方式
	 * @see com.smart.remex.encrypt.RemexBCryptPasswordEncoder
	 * </pre>
	 */
	public static boolean IS_ENCRYPT_BCRYPT = false;
}
