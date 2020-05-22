package com.ygxc.aqjy.shiro.utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import com.ygxc.aqjy.common.enumeration.MsgEnum;
import com.ygxc.aqjy.common.exception.YgxcAqjyServiceException;
import com.ygxc.aqjy.common.utils.ApplicationContextUtils;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.rsp.user.PrincipalDto;
import com.ygxc.aqjy.shiro.CustomToken;
import com.ygxc.aqjy.shiro.dto.LoginResult;


/**
 * shiro工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ShiroUtils {

	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 */
	public static <T> LoginResult<T> login(String username, String password) {	
		try {
			CustomToken customToken = new CustomToken(username,password, "user");
			//UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(customToken);
			LoginResult<T> loginResult = new LoginResult<>();
			loginResult.setUser(getUser());
			loginResult.setSessionId(getSessionId());
			return loginResult;
		} catch (UnknownAccountException e) {
			throw new YgxcAqjyServiceException(MsgEnum.THE_USER_DOESN_T_EXIST);
		} catch (IncorrectCredentialsException e) {
			throw new YgxcAqjyServiceException(MsgEnum.NO_ACCOUNT_OR_PASSWORD_ERROR);
		} catch (Exception e) {
			throw new YgxcAqjyServiceException(MsgEnum.ABNORMAL_OPERATION);
		}
	}
	
	/**
	 * 取得已登录的用户
	 * @param <T>
	 * @return
	 */
	public static <T> T getUser() {
		Subject subject = SecurityUtils.getSubject();
		@SuppressWarnings("unchecked")
		T user = (T) subject.getPrincipal();
		return user;
	}
	
	/**
	 * 取得sessionId
	 * @return
	 */
	public static String getSessionId() {
		Subject subject = SecurityUtils.getSubject();
		String sessionId = subject.getSession().getId().toString();
		return sessionId;
	}
	
	/**
	 * 注销
	 */
	public static void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
	
	/**
	 * 默认密码盐值算法
	 * @param username 用户名
	 * @return
	 */
	public static ByteSource getByteSource(String username) {
		return ByteSource.Util.bytes(username);
	}
	
	/**
	 * 默认密码盐值算法
	 * @param username 用户名
	 * @return
	 */
	public static String getSalt(String username) {
		return ByteSource.Util.bytes(username).toString();
	}
	
	/**
	 * 踢出用户
	 * @param username
	 */
	public static <T> void kickUser(String username) {
		Assist.notBlank(username, "username cannot be blank");
		
		SessionDAO sessionDAO = ApplicationContextUtils.getBean(SessionDAO.class);
		
		//查询当前用户列表
		Collection<Session> list = sessionDAO.getActiveSessions();
		Assist.forEach(list, session -> {
			 PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			PrincipalDto user = (PrincipalDto) principalCollection.getPrimaryPrincipal();
			String curUserName = user.getUsername();
			
			//踢出用户
			if (username.equals(curUserName)) {
				sessionDAO.delete(session);
			}
		});
	}
	
	/**
	 * 获取所有在线用户
	 * @param username
	 * @return 
	 */
	public static List<PrincipalDto> getOnlineUsers() {
		
		List<PrincipalDto> dtoList = new ArrayList<PrincipalDto>();
		SessionDAO sessionDAO = ApplicationContextUtils.getBean(SessionDAO.class);
		//查询当前用户列表
		Collection<Session> list = sessionDAO.getActiveSessions();
		Assist.forEach(list, session -> {
			PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
			PrincipalDto user = (PrincipalDto) principalCollection.getPrimaryPrincipal();
			dtoList.add(user);
		
		});
		return dtoList;
	
	}
}
