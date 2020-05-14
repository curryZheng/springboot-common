package com.ygxc.aqjy.common.structure;

public class RConst {

    /**
     * 处理成功时的code
     */
	public static final Integer SUCCESS_CODE = 0;

    /**
     * 处理成功时的info
     */
	public static final String SUCCESS_INFO = "success";


	
    /**
     * 业务错误
     */
	public static final int ERROR_CODE = 500;

	public  static final String ERROR_INFO = "server error";

    /**
     * 请求参数错误
     */
	public static final int BAD_REQUEST_ERROR_CODE = 400;

    /**
     * 权限错误
     */
	public static final int UNAUTHRORIZED_ERROR_CODE = 401;
	
	/**
	 * 请先登录
	 */
	public static final String PLEASE_LOGIN_FIRST ="{\"code\":0,\"info\":\"请先登录\",\"data\":[]}";
	
	/**
	 * 没有权限
	 */
	public static final String NO_ACCESS_PERMISSIONS="{\"code\":0,\"info\":\"没有权限\",\"data\":[]}";
}
