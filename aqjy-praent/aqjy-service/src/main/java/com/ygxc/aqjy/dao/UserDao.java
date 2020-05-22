package com.ygxc.aqjy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygxc.aqjy.entity.user.UserEntity;
import com.ygxc.aqjy.req.user.UserQueryReq;
import com.ygxc.aqjy.rsp.user.UserDto;
import com.ygxc.aqjy.rsp.user.UserLoginDto;

/**
 * 用户dao
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月8日
 */
public interface UserDao extends BaseMapper<UserEntity> {

	/**
	 * 查询用户列表
	 * @param req
	 * @param page 
	 * @return
	 */
	List<UserEntity> queryUserList(@Param("req") UserQueryReq req, Page<?> page);
	
	/**
	 * 登录查询用户
	 * @param username
	 * @return
	 */
	UserLoginDto findUserByLogin(String username);
}
