package com.ygxc.aqjy.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ygxc.aqjy.entity.SysUser;
import com.ygxc.aqjy.req.user.UserLoginReq;
import com.ygxc.aqjy.rsp.user.UserLoginDto;
import com.ygxc.aqjy.service.UserService;

public class AdminRealm  extends AuthorizingRealm{
	     @Autowired
	    private UserService userService;

	    //shiro的权限信息配置
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	        SysUser SysUser = (SysUser) principals.getPrimaryPrincipal();
	        System.out.println(SysUser);
	        Set<String> roleNames = new HashSet<>();
	        roleNames.add("admin");
	        roleNames.add("manger");
	        //此处把当前subject对应的所有角色信息交给shiro，调用hasRole的时候就根据这些role信息判断
	      
	        Set<String> permissionNames = new HashSet<>();
//	        permissionNames.add("delete");
//	        permissionNames.add("update");
	        //此处把当前subject对应的权限信息交给shiro，当调用hasPermission的时候就会根据这些信息判断
	        authorizationInfo.setStringPermissions(permissionNames);
	        return authorizationInfo;
	    }

	    //根据token获取认证信息authenticationInfo
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	       
	    	System.out.println("adminadminadmin");
	    	
	        String username = (String) token.getPrincipal();
	        UserLoginDto user = userService.findUserByLogin(new UserLoginReq(username)).getData();
	        if (user == null) {
	            return null;
	        }
	      
	        //SimpleAuthenticationInfo还有其他构造方法，比如密码加密算法等，感兴趣可以自己看
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	        		user,                      //表示凭证，可以随便设，跟token里的一致就行
	                user.getPassword(),   //表示密钥如密码，你可以自己随便设，跟token里的一致就行
	                getName()
	        );
	       // authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
	        //authenticationInfo信息交个shiro，调用login的时候会自动比较这里的token和authenticationInfo
	        return authenticationInfo;
	    }
	    
	    @Override
	    public String getName() {
	        return "admin";
	    }
}
