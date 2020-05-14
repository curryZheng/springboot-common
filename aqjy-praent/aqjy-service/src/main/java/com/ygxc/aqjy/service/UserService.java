package com.ygxc.aqjy.service;

import java.util.List;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.UserCreateReq;
import com.ygxc.aqjy.req.user.UserLoginReq;
import com.ygxc.aqjy.req.user.UserModifyReq;
import com.ygxc.aqjy.req.user.UserQueryReq;
import com.ygxc.aqjy.rsp.user.UserDto;
import com.ygxc.aqjy.rsp.user.UserLoginDto;

/**
 * 用户service
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface UserService {

	/**
	 * 创建用户
	 * @param req
	 * @return
	 */
	R<String> createUser(UserCreateReq req);
	
	/**
	 * 修改用户
	 * @param req
	 * @return
	 */
	R<Void> modifyUser(UserModifyReq req);
	
	/**
	 * 登录查询用户
	 * @param req
	 * @return
	 */
	R<UserLoginDto> findUserByLogin(UserLoginReq req);
	
	/**
	 * 根据ID查询用户
	 * @param req
	 * @return
	 */
	R<UserDto> findUserById(OperationByIdReq req);
	
	/**
	 * 删除用户
	 * @param req
	 * @return
	 */
	R<Void> deleteUser(OperationByIdReq req);
	
	/**
	 * 查询用户列表
	 * @param req
	 * @return
	 */
	R<List<UserDto>> queryUserList(UserQueryReq req);
}
