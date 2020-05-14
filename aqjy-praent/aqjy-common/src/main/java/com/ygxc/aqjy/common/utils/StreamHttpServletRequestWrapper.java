package com.ygxc.aqjy.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public StreamHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		requestBody = StreamUtils.copyToByteArray(request.getInputStream());
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
