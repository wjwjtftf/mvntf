/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.soap.exception;

/**
 * 自定义soap异常
 *
 * @author Administrator
 *
 * @version 2015年4月8日
 *
 */
public class UserException extends Exception {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -1883827931226934993L;

	/**
	 * 
	 */
	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
