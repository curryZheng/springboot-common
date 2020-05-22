package com.ygxc.aqjy.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.common.utils.DateUtil;
import com.ygxc.aqjy.common.utils.IdUtil;
import com.ygxc.aqjy.dao.AuthDao;
import com.ygxc.aqjy.dao.RoleAuthDao;
import com.ygxc.aqjy.entity.user.AuthEntity;
import com.ygxc.aqjy.entity.user.RoleAuthEntity;
import com.ygxc.aqjy.framework.base.BaseService;
import com.ygxc.aqjy.req.common.OperationByIdReq;
import com.ygxc.aqjy.req.user.AuthByUrlFindReq;
import com.ygxc.aqjy.req.user.AuthCreateReq;
import com.ygxc.aqjy.req.user.AuthInfoByRoleIdQueryReq;
import com.ygxc.aqjy.req.user.AuthModifyReq;
import com.ygxc.aqjy.req.user.RoleAuthModifyReq;
import com.ygxc.aqjy.rsp.user.AuthDto;
import com.ygxc.aqjy.rsp.user.AuthTreeDto;
import com.ygxc.aqjy.rsp.user.AuthTreeForRoleDto;
import com.ygxc.aqjy.service.AuthService;

/**
 * 权限service
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月15日
 */
@Service
public class AuthServiceImpl extends BaseService implements AuthService {
	
	@Autowired
	private  AuthDao authDao;
	
	@Autowired
	private  RoleAuthDao roleAuthDao;

	/**
	 * 创建权限
	 */
	@Override
	public R<String> createAuth(AuthCreateReq req) {
		AuthEntity entity=convertBean(req, AuthEntity.class);
		entity.setId(IdUtil.generateUUID());
		entity.setCreateTime(DateUtil.getCurTimestamp());
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		authDao.insert(entity);
		return packResult();
	}

	@Override
	public R<Void> modifyAuth(AuthModifyReq req) {
		AuthEntity entity = convertBean(req, AuthEntity.class);
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		judgeDbHdRow(authDao.updateById(entity));
		return packResult();
	}

	@Override
	public R<Void> deleteAuth(OperationByIdReq req) {
		AuthEntity entity = convertBean(req, AuthEntity.class);
		entity.setUpdateTime(DateUtil.getCurTimestamp());
		entity.setIsDelete(BConst.ONE);
		judgeDbHdRow(authDao.updateById(entity));
		return packResult();
	}

	@Override
	public R<List<String>> queryRoleAuthCodeList(AuthInfoByRoleIdQueryReq req) {
		
		return null;
	}

	@Override
	public R<List<String>> queryRoleAuthUrlList(AuthInfoByRoleIdQueryReq req) {
		
		return null;
	}

	@Override
	public R<List<AuthTreeDto>> queryRoleAuthTreeList(AuthInfoByRoleIdQueryReq req) {
		List<AuthEntity> list = authDao.queryRoleAuthTreeList(req.getRoleId());
		List<AuthTreeDto> dtoList = packAuthTreeList(list);
		return packResult(dtoList);
	}

	@Override
	public R<List<AuthTreeDto>> queryAuthTreeList(RequestHead req) {
		List<AuthEntity> list = authDao.selectList(new QueryWrapper<AuthEntity>());
		List<AuthTreeDto> dtoList = packAuthTreeList(list);
		return packResult(dtoList);
	}

	@Override
	public R<List<AuthTreeForRoleDto>> queryAuthTreeForRoleList(AuthInfoByRoleIdQueryReq req) {
		
		return null;
	}

	/**
	 *修稿角色权限
	 */
	@Override
	public R<Void> modifyRoleAuth(RoleAuthModifyReq req) {
		//删除角色的所有权限
		roleAuthDao.delete(new UpdateWrapper<RoleAuthEntity>().lambda()
				                                               .eq(RoleAuthEntity::getRoleId, req.getRoleId()));
		
		Assist.forEach(req.getAuthIdList(), authId -> {
			RoleAuthEntity entity = new RoleAuthEntity();
			entity.setRoleId(req.getRoleId());
			entity.setAuthId(authId);
			roleAuthDao.insert(entity);
		});
		return packResult();
	}

	/**
	 * 根据url查询权限信息
	 */
	@Override
	public R<AuthDto> findAuthByUrl(AuthByUrlFindReq req) {
		AuthEntity entity = authDao.selectOneByUrl(req.getUrl());
		return packResult(convertBean(entity, AuthDto.class));
	}

	/**
	 * 封装权限树列表
	 * @param list
	 * @return
	 */
	private List<AuthTreeDto> packAuthTreeList(List<AuthEntity> list) {
		List<AuthTreeDto> dtoList = new ArrayList<>();
		
		Assist.forEach(list, entity -> {
			//第一级节点
			if (Assist.isBlank(entity.getParentId())) {
				AuthTreeDto dto = convertBean(entity, AuthTreeDto.class);
				dtoList.add(dto);
				
				//封装子列表
				packAuthTreeList(dto, list);
			}
		});
		
		return dtoList;
	}
	
	/**
	 * 递归封装权限树列表
	 * @param parentDto
	 * @param list
	 */
	private void packAuthTreeList(AuthTreeDto parentDto, List<AuthEntity> list) {
		Assist.forEach(list, entity -> {
			if (parentDto.getId().equals(entity.getParentId())) {
				AuthTreeDto dto = convertBean(entity, AuthTreeDto.class);
				parentDto.getChildList().add(dto);
				
				//封装子列表
				packAuthTreeList(dto, list);
			}
		});
	}
	
	/**
	 * 提取当前权限的所有子权限ID
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<String> fetchAllChildAuthId(String id) {
		List<String> idList = new ArrayList<>();
		idList.add(id);
		List<AuthEntity> entityList = authDao.selectList(null);
		fetchAllChildAuthId(id, idList, entityList);
		return idList;
	}
	
	/**
	 * 提取当前权限的所有子权限ID
	 * @param curId
	 * @param idList
	 * @param entityList
	 */
	private void fetchAllChildAuthId(String curId, List<String> idList, List<AuthEntity> entityList) {
		for (AuthEntity entity : entityList) {
			if (curId.equals(entity.getParentId())) {
				idList.add(entity.getId());
				fetchAllChildAuthId(entity.getId(), idList, entityList);
			}
		}
	}
	
}
