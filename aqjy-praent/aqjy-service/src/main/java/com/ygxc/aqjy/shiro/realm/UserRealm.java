package com.ygxc.aqjy.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ygxc.aqjy.common.constant.AqjyExcludeUrlOptions;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.utils.Assert;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.dao.AuthDao;
import com.ygxc.aqjy.req.user.UserLoginReq;
import com.ygxc.aqjy.rsp.user.PrincipalDto;
import com.ygxc.aqjy.rsp.user.UserLoginDto;
import com.ygxc.aqjy.service.UserService;
import com.ygxc.aqjy.shiro.utils.ShiroUtils;

/**
 * 用户realm
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月13日
 */
public class UserRealm  extends AuthorizingRealm{
	
	    @Autowired
	    private UserService userService;

	    @Autowired
	    private AuthDao authDao;
	    
	    //shiro的权限信息配置
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();	      	        
	        PrincipalDto userLoginDto = (PrincipalDto) principals.getPrimaryPrincipal();	    
	        Assert.notBlank(userLoginDto.getRoleId(), "roleId cannot be blank");	
			Set<String> permissions = new HashSet<String>();		
			List<String> list = authDao.queryRoleAuthUrlList(userLoginDto.getRoleId());
			Assist.forEach(list, permission -> {
				if (Assist.isNotBlank(permission)) {
					String[] permissionArr = permission.split(BConst.BLANK);
					CollectionUtils.addAll(permissions, permissionArr);
				}
			});	
			//加入默认不鉴权的url列表
			permissions.addAll(AqjyExcludeUrlOptions.NOT_AUTHORIZATION_LIST);	
			authorizationInfo.setStringPermissions(permissions);
	        return authorizationInfo;
	    }

	    //根据token获取认证信息authenticationInfo
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	       String username = (String) token.getPrincipal();
	        UserLoginDto loginDto = userService.findUserByLogin(new UserLoginReq(username)).getData();
	        if (loginDto == null) {
	            return null;
	        }
	        //缓存中得实体对象
	        PrincipalDto shiroUserInfoDto=  new PrincipalDto();
	        fillCreate(shiroUserInfoDto,loginDto);
	        
	        //SimpleAuthenticationInfo还有其他构造方法，比如密码加密算法等，感兴趣可以自己看
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	        		shiroUserInfoDto,                      //表示凭证，可以随便设，跟token里的一致就行
	        		loginDto.getPassword(),   //表示密钥如密码，你可以自己随便设，跟token里的一致就行
	                getName()
	        );

	        return authenticationInfo;
	    }
	    
	    /**
	     * 重写getName
	     */
	    @Override
	    public String getName() {
	        return "user";
	    }
	    
		/**
		 * 取得授权缓存的key
		 */
		@Override
		protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
			return ShiroUtils.getSessionId();
		}
		
		protected void fillCreate(PrincipalDto dto, UserLoginDto mainDto) {
			dto.setId(mainDto.getId());
			dto.setUsername(mainDto.getUsername());
			dto.setName(mainDto.getName());
			dto.setUserNo(mainDto.getUserNo());
			dto.setRoleId(mainDto.getRoleId());
		}
	   
}
