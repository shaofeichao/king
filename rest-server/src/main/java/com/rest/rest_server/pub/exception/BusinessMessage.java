package com.rest.rest_server.pub.exception;

/**
 * 自定义EXCEPTION,用于处理业务异常
 */
public class BusinessMessage extends Exception {
	/**
	 * 输出异常消息
	 */
    public BusinessMessage(String message) {
        super(message);
    }
}
