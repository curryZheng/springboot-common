package com.ygxc.aqjy.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.dao.UserDao;
import com.ygxc.aqjy.entity.user.UserEntity;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.UserCreateReq;
import com.ygxc.aqjy.req.user.UserLoginReq;
import com.ygxc.aqjy.req.user.UserModifyReq;
import com.ygxc.aqjy.req.user.UserQueryReq;
import com.ygxc.aqjy.rsp.user.UserDto;
import com.ygxc.aqjy.rsp.user.UserLoginDto;
import com.ygxc.aqjy.service.UserService;
/**
 * 用户service
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public R<String> createUser(UserCreateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> modifyUser(UserModifyReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<UserLoginDto> findUserByLogin(UserLoginReq req) {
		UserEntity   entity=userDao.selectOne(new QueryWrapper<UserEntity>()
				                                  .lambda()
				                                  .eq(UserEntity::getUsername, req.getUsername())
				                                  .eq(UserEntity::getIsDelete, BConst.NO));
		return packResult(convertBean(entity, UserLoginDto.class));
	}

	@Override
	public R<UserDto> findUserById(OperationByIdReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> deleteUser(OperationByIdReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<UserDto>> queryUserList(UserQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}


}
