package com.ygxc.aqjy.framework.filter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.common.utils.ServeletUtils;
import com.ygxc.aqjy.common.utils.SocketUtils;
import com.ygxc.aqjy.common.utils.StringUtil;


/**
 * 基础过滤器
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
public abstract class BaseFilter  implements Filter {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/** 排除的url列表 */
	protected List<String> excludeUrlList = new ArrayList<>();
	
	/**
	 * 验证此url是否排除
	 * @param url
	 * @return
	 */
	protected boolean isExclude(String url) {
		HttpServletRequest request = ServeletUtils.getRequest();
		if(StringUtil.equals(request.getMethod().toString(), "GET")) {
			return true;
		}
		return SocketUtils.includeUrl(url, excludeUrlList);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}
	
	/**
	 * 添加排除的url
	 * @param excludeUrl
	 */
	public void addExcludeUrl(String...excludeUrl) {
		Assist.add(excludeUrlList, excludeUrl);
	}
	
	/**
	 * 添加排除的url
	 * @param excludeUrl
	 */
	public void addExcludeUrl(List<String> list) {
		Assist.add(excludeUrlList, list);
	}

	public List<String> getExcludeUrlList() {
		return excludeUrlList;
	}

	public void setExcludeUrlList(List<String> excludeUrlList) {
		this.excludeUrlList = excludeUrlList;
	}
}