package com.ygxc.aqjy.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;
import com.alibaba.fastjson.JSONObject;
import com.ygxc.aqjy.common.exception.YgxcAqjyServiceException;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.common.utils.DateUtil;
import com.ygxc.aqjy.common.utils.StreamHttpServletRequestWrapper;
import com.ygxc.aqjy.common.utils.StringUtil;
import com.ygxc.aqjy.dao.AuthDao;
import com.ygxc.aqjy.entity.user.AuthEntity;
import com.ygxc.aqjy.framework.filter.BaseFilter;
import com.ygxc.aqjy.req.oplog.OpLogCreateReq;
import com.ygxc.aqjy.rsp.user.PrincipalDto;
import com.ygxc.aqjy.service.OpLogService;
import com.ygxc.aqjy.shiro.utils.ShiroUtils;

/**
 * 操作日志过滤器
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月14日
 */
@ConditionalOnExpression("${oplogSwitch:false}")
@Component
public class OpLogFilter extends BaseFilter {

    //添加默认排除掉得url 
	public OpLogFilter() {
		Assist.add(excludeUrlList,  "/", "/csrf"
				                    ,"/swagger-ui.html",
				                    "/swagger-resources",
				                    "/swagger-resources/configuration/security",
				                    "/swagger-resources/configuration/ui",
				                    "/v2/api-docs",
				                    "/webjars/springfox-swagger-ui/**");
	}

	
	@Autowired
	private  OpLogService opLogService;
	
	@Autowired
	private  AuthDao authDao;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		AuthEntity authEntity = null;
		String url = null;
		String body = null;
		String rspBody = null;
		R<Void> result = null;
		boolean exclude = true;
		
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			
			//请求url
			url = httpServletRequest.getRequestURI();
			//验证此url是否排除
			exclude = isExclude(url);
			//排除此url
			if (exclude) {
				chain.doFilter(request, response);
			} else {
				StreamHttpServletRequestWrapper requestWrapper = new StreamHttpServletRequestWrapper(httpServletRequest);
				ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
				
				//请求参数
				body = StreamUtils.copyToString(httpServletRequest.getInputStream(), Charset.forName("UTF-8"));
				
				
				chain.doFilter(requestWrapper, responseWrapper);
				
				//读取值
				String contentType = Assist.defaultBlank(responseWrapper.getContentType());
				//json格式返回值
				if (contentType.indexOf("application/json") != -1) {
					rspBody = new String(responseWrapper.getContentAsByteArray());
					result = toResult(rspBody);	
				} 
				else {
					result = R.ok();
				}
				responseWrapper.copyBodyToResponse();
			}
			
		} catch (YgxcAqjyServiceException e) {
			result = R.error(e.getMsgEnum());
		} catch (Exception e) {
			result = R.error();
		} finally {
			if (!exclude) {
				try {
					//当前url权限
					authEntity = authDao.selectOneByUrl( url);
					//取得当前户
					PrincipalDto user = ShiroUtils.getUser();
					OpLogCreateReq req = new OpLogCreateReq();
					fillCreate(req, url, authEntity, body, user, result, rspBody);
					opLogService.createOpLog(req);
					
				} catch (Exception e2) {
					logger.error("create op log error", e2);
				} 
			}
		}
		
	}

	/**
	 * 尝试转换为结果集
	 * @param body
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected R<Void> toResult(String body) {
		R<Void> result = null;
		try {
			result = JSONObject.parseObject(body, R.class);
		} catch (Exception e) {
			//转换失败不做处理
		}
		return result;
	}
	
	protected void fillCreate(OpLogCreateReq req, String url, AuthEntity authEntity, String body
			, PrincipalDto user, R<Void> result, String rspBody) {
		req.setOpTime(DateUtil.getCurTimestamp());
		req.setFnUrl(url);
		req.setReq(body);
		if(StringUtil.equals(url, "/login")) {
			req.setReq("");
			req.setFnName("系统登陆");
		}else {
			if (authEntity != null) {
				req.setFnId(authEntity.getId());
				req.setFnCode(authEntity.getCode());
				req.setFnName(authEntity.getName());
			}
		}		
		if (user != null) {
			req.setOpUserId(user.getId());
			req.setOpUserName(user.getUsername());
			req.setOpUserNo(user.getUserNo());
		}
		
		if (result != null) {
			req.setResult(result.judgeSuccessInt());	
			if (Assist.isNotBlank(rspBody)) {
				req.setRsp(rspBody);
			} else {
				req.setRsp(JSONObject.toJSONString(result));
			}
			
		}
	}
	
}
