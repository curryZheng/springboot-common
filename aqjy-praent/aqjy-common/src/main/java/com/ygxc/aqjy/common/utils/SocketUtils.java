package com.ygxc.aqjy.common.utils;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collection;


/**
 * socketUtile工具类
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
public class SocketUtils  {
	
	/** 一星通配符 */
	public final static String WILDCARD_FIRST  = "/*";
	
	/** 二星通配符 */
	public final static String WILDCARD_SECOND = "/**";

	/**
	 * ip + 端口，是否可连接
	 * @param host
	 * @param port
	 * @return
	 */
	public static boolean isConnectable(String host, int port) {
		if (Assist.isBlank(host)) {
			return false;
		}
		
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			
			SocketAddress socketAddress = new InetSocketAddress(host, port);
			socket.connect(socketAddress, 250);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 是否是否包含此url
	 * @param urlList
	 * @return
	 */
	public static boolean includeUrl(String url, Collection<String> urlList) {
		Assist.notBlank(url, "url cannot be blank");
		for (String curUrl : urlList) {
			if (Assist.isBlank(curUrl)) continue;
			//url匹配
			if (url.equals(curUrl)) return true;
			//一星通配符
			if (curUrl.endsWith(WILDCARD_FIRST)) {
				String itemUrl = curUrl.substring(0, curUrl.length() - 2);
				if (url.startsWith(itemUrl)) return true;
			}
			//二星通配符
			if (curUrl.endsWith(WILDCARD_SECOND)) {
				String itemUrl = curUrl.substring(0, curUrl.length() - 3);
				if (url.startsWith(itemUrl)) return true;
			}
		}
		return false;
	}
}