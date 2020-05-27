package com.ygxc.aqjy.shiro.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.exception.YgxcAqjyServiceException;
import com.ygxc.aqjy.common.utils.Assert;
import com.ygxc.aqjy.common.utils.BeanUtils;
import com.ygxc.aqjy.framework.core.redis.RedisProperties;
import com.ygxc.aqjy.shiro.ShiroOptions;
import com.ygxc.aqjy.shiro.properties.ShiroProperties;
import com.ygxc.aqjy.shiro.realm.AdminRealm;
import com.ygxc.aqjy.shiro.realm.CustomModularRealmAuthenticator;
import com.ygxc.aqjy.shiro.realm.CustomizedModularRealmAuthorizer;
import com.ygxc.aqjy.shiro.realm.UserRealm;

@Configuration
public class ShiroConfig {

	
	/**
	 * shiro properties
	 * @return
	 */
	@Bean
	@ConfigurationProperties(ShiroProperties.SHIRO_PREFIX)
	@ConditionalOnMissingBean
	public ShiroProperties createFastShiroProperties() {
		ShiroProperties properties = new ShiroProperties();
		return properties;
	}
	
	/**
	 * shiro核心安全事务管理器
	 * @param matcher
	 * @param sessionManager
	 * @param redisCacheManager
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(HashedCredentialsMatcher matcher,SessionManager sessionManager,
			RedisCacheManager redisCacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置校验的Realm对象
		securityManager.setAuthenticator(authenticator());
		List<Realm> realms = new ArrayList<Realm>();
		realms.add(userRealm(matcher));
		realms.add(adminRealm(matcher));
		securityManager.setRealms(realms);
		CustomizedModularRealmAuthorizer authorizer = new CustomizedModularRealmAuthorizer();
		authorizer.setRealms(realms);
		securityManager.setAuthorizer(authorizer);
		securityManager.setSessionManager(sessionManager);
		securityManager.setCacheManager(redisCacheManager);
		return securityManager;
	}

	@Bean
	public UserRealm userRealm(HashedCredentialsMatcher matcher) {
		UserRealm userRealm = new UserRealm();
		// 在这里配置密码加密
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}

	@Bean
	public AdminRealm adminRealm(HashedCredentialsMatcher matcher) {
		AdminRealm adminRealm = new AdminRealm();
		// 在这里配置密码加密
		adminRealm.setCredentialsMatcher(matcher);
		return adminRealm;
	}

	@Primary
	@Bean("shiroFilterRegistration")
	public FilterRegistrationBean<AbstractShiroFilter> createShiroFilter(SecurityManager securityManager) {
		try {
			ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
			factoryBean.setSecurityManager(securityManager);
			// 取得shiro所用的拦截器列表
			Map<String, Filter> filters = getFilters();
			if (filters != null) {
				factoryBean.setFilters(filters);
			}
			// 配置访问权限
			Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();// 拦截器, 配置不会被拦截的链接 顺序判断
			filterChainDefinitionMap.put("/login", "anon");
			filterChainDefinitionMap.put("/adminLogin", "anon");// 所有匿名用户均可访问到Controller层的该方法下
			filterChainDefinitionMap.put("/userLogin", "anon");
			filterChainDefinitionMap.put("/swagger-ui.html", "anon");
			filterChainDefinitionMap.put("/swagger-resources/**", "anon");
			filterChainDefinitionMap.put("/v2/**", "anon");
			filterChainDefinitionMap.put("/webjars/**", "anon");
			filterChainDefinitionMap.put("/image/**", "anon");
			filterChainDefinitionMap.put("/css/**", "anon");
			filterChainDefinitionMap.put("/fonts/**", "anon");
			filterChainDefinitionMap.put("/js/**", "anon");
			filterChainDefinitionMap.put("/logout", "logout");
			filterChainDefinitionMap.put("/**", "authc"); // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
			factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			AbstractShiroFilter filter = (AbstractShiroFilter) factoryBean.getObject();
			FilterRegistrationBean<AbstractShiroFilter> filterRegistrationBean = new FilterRegistrationBean<AbstractShiroFilter>();
			filterRegistrationBean.setOrder(10);
			filterRegistrationBean.setFilter(filter);
			filterRegistrationBean.setName("shiroFilter");
			filterRegistrationBean.addUrlPatterns("/*");
			return filterRegistrationBean;
		} catch (Exception e) {
			throw new YgxcAqjyServiceException("create shiro filter error");
		}
	}

	/**
	 * 密码匹配凭证管理器（密码加密需要此配置）
	 * 
	 * @return
	 */
	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(1);// 设置加密次数
		return hashedCredentialsMatcher;
	}

	// 如果没有这两个配置，可能会授权失败，所以依赖中还需要配置aop的依赖
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(HashedCredentialsMatcher matcher,SessionManager sessionManager,
			RedisCacheManager redisCacheManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager(matcher, sessionManager, redisCacheManager));
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	// 认证器
	@Bean
	public CustomModularRealmAuthenticator authenticator() {
		CustomModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
		return authenticator;
	}

	/**
	 * 取得shiro所用的拦截器列表
	 * 
	 * @return
	 */
	protected Map<String, Filter> getFilters() {
		Map<String, Filter> filters = new HashMap<>();
		filters.put("authc", new com.ygxc.aqjy.shiro.filter.ShiroAuthenticationFilter());
		return filters;
	}

	/**
	 * shiro SessionManager
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DefaultSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
		DefaultSessionManager sessionManager = BeanUtils.newInstance(ShiroOptions.SESSION_MANAGER_CLASS);
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionDAO(redisSessionDAO);
		return sessionManager;
	}

	/**
	 * shiro redis manager，支持redis集群环境
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IRedisManager createRedisManager(RedisProperties redisProperties) {
		Assert.notBlank(redisProperties.getHostName(), "redis hostName cannot be blank");

		// 以,号分割redis地址，如果只有一个，那就是单点，如果有多个那就是集群
		String[] hostNameArr = redisProperties.getHostName().split(getRedisClusterHostNameSplit());

		IRedisManager bean = null;

		// redis集群
		if (hostNameArr.length != 1) {
			bean = createRedisManagerCluster(redisProperties);
		} else { // redis单点
			bean = createRedisManagerSingle(redisProperties);
		}

		return bean;
	}

	/**
	 * shiro redis session dao
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisSessionDAO createRedisSessionDAO(IRedisManager redisManager) {
		RedisSessionDAO bean = new RedisSessionDAO();
		bean.setRedisManager(redisManager);
		bean.setKeyPrefix("shiro-aqjy:session:");
		return bean;
	}

	/**
	 * shiro redis session dao
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisCacheManager createRedisCacheManager(IRedisManager redisManager) {
		RedisCacheManager bean = new RedisCacheManager();
		bean.setRedisManager(redisManager);
		bean.setKeyPrefix("shiro-aqjy:cache:");
		return bean;
	}

	/**
	 * 创建集群的shiro redis manager
	 * 
	 * @param redisProperties
	 * @return
	 */
	protected IRedisManager createRedisManagerCluster(RedisProperties redisProperties) {
		Assert.notBlank(redisProperties.getHostName(), "redis hostName cannot be blank");

		RedisClusterManager bean = new RedisClusterManager();
		String newHostName = redisProperties.getHostName().replace(getRedisClusterHostNameSplit(), BConst.COMMA);
		bean.setHost(newHostName);
		if (redisProperties.getDatabase() != null) {
			bean.setDatabase(redisProperties.getDatabase());
		}
		if (StringUtils.isNotBlank(redisProperties.getPassword())) {
			bean.setPassword(redisProperties.getPassword());
		}
		return bean;
	}

	/**
	 * 创建集群的shiro redis manager
	 * 
	 * @param redisProperties
	 * @return
	 */
	protected IRedisManager createRedisManagerSingle(RedisProperties redisProperties) {
		RedisManager bean = new RedisManager();
		bean.setHost(redisProperties.getHostName());
		if (redisProperties.getDatabase() != null) {
			bean.setDatabase(redisProperties.getDatabase());
		}
		if (StringUtils.isNotBlank(redisProperties.getPassword())) {
			bean.setPassword(redisProperties.getPassword());
		}
		return bean;
	}

	/**
	 * 取得redis集群的hostName分割符
	 * 
	 * @return
	 */
	protected String getRedisClusterHostNameSplit() {
		return ";";
//		return BConst.COMMA;
	}
}
