package com.ygxc.aqjy.common.exception;

/**
 * <p>
 * </p>
 *
 * @author LeiZheng
 * @date 2019/6/10 0014 下午 1:39
 */
public class YgxcBusinessException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public YgxcBusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public YgxcBusinessException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public YgxcBusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public YgxcBusinessException(String message,String...msgArgs) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public YgxcBusinessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public YgxcBusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}