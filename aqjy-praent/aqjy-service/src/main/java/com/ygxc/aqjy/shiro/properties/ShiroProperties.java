package com.ygxc.aqjy.shiro.properties;

import org.springframework.beans.factory.InitializingBean;

import com.ygxc.aqjy.shiro.ShiroOptions;


/**
 * ShiroProperties
 * @author curry
 * @email  zhenglei159357@qq.com
 * @date   2020年5月27日
 */
public class ShiroProperties  implements InitializingBean{

	/** shiro properties配置文件前缀 */
	public static final String SHIRO_PREFIX = "shiro";
	
	/** 是否启用开发模式 */
	protected boolean dev = false;
	
	/** 是否开启权限验证 */
	protected boolean disabledPermit = false;
	
	/** session超时时间，单位秒，默认30分钟 */
	protected long globalSessionTimeout = 1800000;
	
	public boolean isDev() {
		return dev;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public long getGlobalSessionTimeout() {
		return globalSessionTimeout;
	}

	public void setGlobalSessionTimeout(long globalSessionTimeout) {
		this.globalSessionTimeout = globalSessionTimeout;
	}
	
	public boolean isDisabledPermit() {
		return disabledPermit;
	}

	public void setDisabledPermit(boolean disabledPermit) {
		this.disabledPermit = disabledPermit;
	}

	/***
	 * 加载bean初始化ShiroOptions数据
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		ShiroOptions.DEV = this.dev;
		ShiroOptions.DISABLED_PERMIT = this.disabledPermit;
		
	}
}
