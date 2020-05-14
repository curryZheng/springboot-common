package com.ygxc.aqjy.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ygxc.aqjy.common.enumeration.MsgEnum;
import com.ygxc.aqjy.common.exception.YgxcAqjyServiceException;
import com.ygxc.aqjy.common.structure.R;
import com.ygxc.aqjy.common.structure.RequestHead;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.dao.AuthDao;
import com.ygxc.aqjy.dao.RoleAuthDao;
import com.ygxc.aqjy.entity.user.AuthEntity;
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
 * 
 * @author Qiaoxin.Hong
 *
 */
@Service
public class AuthServiceImpl extends BaseService implements AuthService {
	
	@Autowired
	private  AuthDao authDao;
	
	@Autowired
	private  RoleAuthDao roleAuthDao;

	@Override
	public R<String> createAuth(AuthCreateReq req) {
	    throw new YgxcAqjyServiceException(MsgEnum.NO_ACCOUNT_OR_PASSWORD_ERROR);
		
		
	}

	@Override
	public R<Void> modifyAuth(AuthModifyReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> deleteAuth(OperationByIdReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<String>> queryRoleAuthCodeList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<String>> queryRoleAuthUrlList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<List<AuthTreeForRoleDto>> queryAuthTreeForRoleList(AuthInfoByRoleIdQueryReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<Void> modifyRoleAuth(RoleAuthModifyReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R<AuthDto> findAuthByUrl(AuthByUrlFindReq req) {
		// TODO Auto-generated method stub
		return null;
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
