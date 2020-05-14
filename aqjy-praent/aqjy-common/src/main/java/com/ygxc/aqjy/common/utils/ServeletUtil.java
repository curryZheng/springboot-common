package com.ygxc.aqjy.common.utils;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServeletUtil {
	/**
	 * 取得HttpServletResponse
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();;
		return response;
	}

	/**
	 * 取得HttpServletRequest
	 * @return
	 */
    public static HttpServletRequest getRequest() {
    	ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
       
    	return  servletRequestAttributes.getRequest();
	}
    
	/**
	 * 取得HttpServletRequest
	 * @return
	 */
    public static String getRequestIp() {
    	
    	HttpServletRequest request= getRequest();
    	  String ip = request.getHeader("x-forwarded-for");   
          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
              ip = request.getHeader("Proxy-Client-IP");   
          }   
          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
              ip = request.getHeader("WL-Proxy-Client-IP");   
          }   
          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
              ip = request.getRemoteAddr();   
              if(ip.equals("127.0.0.1")){     
                  //根据网卡取本机配置的IP     
                  InetAddress inet=null;     
                  try {     
                      inet = InetAddress.getLocalHost();     
                  } catch (Exception e) {     
                      e.printStackTrace();     
                  }     
                  ip= inet.getHostAddress();     
              }  
          }   
          // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
          if(ip != null && ip.length() > 15){    
              if(ip.indexOf(",")>0){     
                  ip = ip.substring(0,ip.indexOf(","));     
              }     
          }     
          return ip;  

	}

}
