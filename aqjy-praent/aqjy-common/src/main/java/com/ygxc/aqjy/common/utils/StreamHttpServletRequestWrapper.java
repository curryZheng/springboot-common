package com.ygxc.aqjy.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

/**
 * 可重复提取inputStream
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
public class StreamHttpServletRequestWrapper  extends HttpServletRequestWrapper {
	
	/** 请求体 */
	protected byte[] requestBody = null;
	// 所有参数的Map集合
	private Map<String, String[]> parameterMap; 
	
	public StreamHttpServletRequestWrapper(HttpServletRequest request,String body) throws IOException {
		super(request);
		if(StringUtil.isNoneBlank(body)) {
			requestBody = body.getBytes();
		}
		else {
			requestBody = new byte[0];
		}
		parameterMap =new HashMap<String, String[]>(request.getParameterMap());
	}
	
	
    /**
     * 获取所有参数名
     *
     * @return 返回所有参数名
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<String>(parameterMap.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name 指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        return results[0];
    }


    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
   	
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
   }
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
		
		return new ServletInputStream() {
			
			@Override
			public int read() throws IOException {
				return bais.read();
			}
			
			@Override
			public void setReadListener(ReadListener listener) {
				
			}
			
			@Override
			public boolean isReady() {
				return false;
			}
			
			@Override
			public boolean isFinished() {
				return false;
			}
		};
	}
	
	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}
}
