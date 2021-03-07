package com.rest.rest_server.pub.exception;

/**
 * 自定义EXCEPTION
 */
public class ExceptionMessage  extends Exception {
	/**
	 * 输出异常消息
	 */
    public ExceptionMessage(String message) {
        super(message);
    }
}
