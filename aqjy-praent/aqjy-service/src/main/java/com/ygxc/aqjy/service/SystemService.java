package com.ygxc.aqjy.service;

import java.util.List;

import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.req.sys.LoginReq;
import com.ygxc.aqjy.req.sys.kickUserReq;
import com.ygxc.aqjy.rsp.sys.LoginDto;
import com.ygxc.aqjy.rsp.user.PrincipalDto;

/**
 * 系统service
 * 
 * @author curry
 *
 */
public interface SystemService {

	/**
	 * 登录
	 * @param req
	 * @return
	 */
	R<LoginDto> login(LoginReq req);
	
	/**
	 * 登出
	 * @param req
	 * @return
	 */
	R<Void> logout(RequestHead req);

	/**
	 * 获取所有在线用户
	 * @return
	 */
	R<List<PrincipalDto>> getOnlineUsers();

	/**
	 * 提出登陆用户
	 * @param req 
	 * @return
	 */
	R<Void> kickUser(kickUserReq req);
}
