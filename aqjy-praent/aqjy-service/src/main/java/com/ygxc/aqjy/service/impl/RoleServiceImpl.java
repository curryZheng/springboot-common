package com.ygxc.aqjy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.structure.PageR;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.utils.DateUtil;
import com.ygxc.aqjy.common.utils.IdUtil;
import com.ygxc.aqjy.dao.RoleDao;
import com.ygxc.aqjy.entity.user.RoleEntity;
import com.ygxc.aqjy.framework.annotation.AqjyValidate;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.RoleCreateReq;
import com.ygxc.aqjy.req.user.RoleModifyReq;
import com.ygxc.aqjy.req.user.RoleQueryReq;
import com.ygxc.aqjy.rsp.user.RoleDto;
import com.ygxc.aqjy.service.RoleService;

/**
 * 角色service
 * 
 * @author
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	
	@Autowired
	private  RoleDao roleDao;

	/**
	 * 创建角色
	 */
	@AqjyValidate
	@Override
	public R<String> createRole(RoleCreateReq req) {
		RoleEntity entity = convertBean(req, RoleEntity.class);
		entity.setId(IdUtil.generateUUID());
		entity.setCreateTime(DateUtil.getCurTimestamp());
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		judgeDbHdRow(roleDao.insert(entity));;
		return packResult();
	}

	/**
	 * 修改角色
	 */
	@AqjyValidate
	@Override
	public R<Void> modifyRole(RoleModifyReq req) {
		RoleEntity entity = convertBean(req, RoleEntity.class);
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		judgeDbHdRow(roleDao.updateById(entity));;
		return packResult();
	}

	/**
	 * 删除角色
	 */
	@AqjyValidate
	@Override
	public R<Void> deleteRole(OperationByIdReq req) {
		RoleEntity entity = convertBean(req, RoleEntity.class);
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		entity.setIsDelete(BConst.ONE);
		judgeDbHdRow(roleDao.updateById(entity));;
		return packResult();
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public PageR<List<RoleDto>> queryRoleList(RoleQueryReq req) {
		startPage(req.getPageNum(), req.getPageSize());
		List<RoleEntity> roleEntities = roleDao.queryRoleList(req,getPage());
		return packPageResult(getPage(), convertBeanList(roleEntities, RoleDto.class));
	}

	
}
