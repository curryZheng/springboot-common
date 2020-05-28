package com.ygxc.aqjy.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.utils.DateUtil;
import com.ygxc.aqjy.common.utils.IdUtil;
import com.ygxc.aqjy.common.utils.MD5Util;
import com.ygxc.aqjy.dao.UserDao;
import com.ygxc.aqjy.entity.user.UserEntity;
import com.ygxc.aqjy.framework.annotation.AqjyValidate;
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
 * 
 *
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
	
	@Autowired
	private  UserDao userDao;

	/**
	 * 创建用户信息
	 */
	@AqjyValidate
	@Override
	public R<String> createUser(UserCreateReq req) {
		UserEntity entity=convertBean(req, UserEntity.class);
		entity.setCreateTime(DateUtil.getCurTimestamp());
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		entity.setPassword(MD5Util.MD5Encode(req.getPassword()));
		entity.setId(IdUtil.generateUUID());
		userDao.insert(entity);
		return packResult();
	}

	/**
	 * 修改用户信息
	 */
	@AqjyValidate
	@Override
	public R<Void> modifyUser(UserModifyReq req) {
		UserEntity entity=convertBean(req, UserEntity.class);
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		judgeDbHdRow(userDao.updateById(entity));
		return packResult();
	}
    
	/**
	 * 根据用户登陆名查询用户细腻
	 */
	@Override
	public R<UserLoginDto> findUserByLogin(UserLoginReq req) {
		 UserEntity   entity=userDao.selectOne(new QueryWrapper<UserEntity>()
				                                  .lambda()
				                                  .eq(UserEntity::getUsername, req.getUsername())
				                                  .eq(UserEntity::getIsDelete, BConst.NO));
		return packResult(convertBean(entity, UserLoginDto.class));
	}

	/**
	 * 根据用户Id查询用户信息
	 */
	@Override
	public R<UserDto> findUserById(OperationByIdReq req) {
		UserEntity  entity = userDao.selectOne(new QueryWrapper<UserEntity>()
											                .lambda()
											                .eq(UserEntity::getId, req.getId())
											                .eq(UserEntity::getIsDelete, BConst.NO));
		return packResult(convertBean(entity, UserDto.class));
	}

	/**
	 * 删除用户
	 */
	@AqjyValidate
	@Override
	public R<Void> deleteUser(OperationByIdReq req) {
		UserEntity entity=convertBean(req, UserEntity.class);
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		entity.setIsDelete(BConst.ONE);
		return packResult();
	}

	/**
	 * 查询用户列表
	 */
	@Override
	public PageR<List<UserDto>> queryUserList(UserQueryReq req) {
		startPage(req.getPageNum(), req.getPageSize());
		List<UserEntity> entityList = userDao.queryUserList(req,getPage());
		return packPageResult(getPage(), convertBeanList(entityList, UserDto.class) );
	}

}
