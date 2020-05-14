package com.ygxc.aqjy.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;

public class ServeletUtils {
private static Logger logger = LoggerFactory.getLogger(ServeletUtils.class);
	
	/**
	 * 取得HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 取得HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();;
		return response;
	}
	
	/**
	 * 从HttpServletResponse中取得OutputStream
	 * @return
	 */
	public static OutputStream getOutputStream() {
		try {
			return getResponse().getOutputStream();
		} catch (Exception e) {
			throw new YgxcBusinessException("get outputStream error", e);
		}
	}
	
	/**
	 * 从HttpServletResponse中取得下载用的OutputStream
	 * @return
	 */
	public static OutputStream getOutputStream(String srcFilePath) {
		return FileUtils.getOsForDownloadResponse(getResponse(), srcFilePath);
	}
	
	/**
	 * 从HttpServletRequest中取得json参数
	 * @return
	 */
	public static String getJsonParams() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getRequest().getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			reader.read();
			String str;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			
			return sb.toString();
		} catch (Exception e) {
			throw new YgxcBusinessException("get json error", e);
		}
	}
	
	/**
	 * HttpServletResponse写入数据
	 * @param response
	 * @param val
	 */
	public static void write(ServletResponse response, Object obj) {
		String val = obj == null ? "{}" : JSONObject.toJSONString(obj);
		write(response, val);
	}
	
	/**
	 * HttpServletResponse写入数据
	 * @param response
	 * @param val
	 */
	public static void write(ServletResponse response, String val) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(val);
		} catch (Exception e) {
			logger.error("response write error", e);
		}
	}
}
