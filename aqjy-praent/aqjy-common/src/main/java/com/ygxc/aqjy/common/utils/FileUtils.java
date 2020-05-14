package com.ygxc.aqjy.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;

/**
 * 文件工具类
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
public class FileUtils{

	/**
	 * 创建FileInputStream
	 * @param file
	 * @return
	 */
	public static FileInputStream createFileInputStream(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new YgxcBusinessException("create FileInputStream error", e);
		}
	}
	
	/**
	 * 提取HttpServletResponse下载用的OutputStream
	 * @param response
	 * @param srcFilePath
	 * @return
	 */
	public static OutputStream getOsForDownloadResponse(HttpServletResponse response, String srcFilePath) {
		try {
			//文件名
			if (srcFilePath.indexOf("/") != -1) {
				srcFilePath = srcFilePath.substring(srcFilePath.lastIndexOf("/") + 1, srcFilePath.length());
			} 
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition,Access-Token,Uid");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(srcFilePath, "UTF-8"));
			
			return response.getOutputStream();
		} catch (Exception e) {
			throw new YgxcBusinessException("get os for download response error", e);
		}
	}
}

