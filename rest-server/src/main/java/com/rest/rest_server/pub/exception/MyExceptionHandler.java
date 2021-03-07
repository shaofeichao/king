package com.rest.rest_server.pub.exception;

import com.rest.rest_server.pub.exception.bean.BaseRspsMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义捕获异常抛出
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
	/**
	 * 自定义捕获异常抛出
	 */
    @ExceptionHandler(ExceptionMessage.class)
    public String handlerMyException(ExceptionMessage ex) {
        log.error(" error MyExceptionHandler checkOrder handlerMyException ：" + ex.getMessage());
        BaseRspsMsg baseRspsMsg = BaseRspsMsg.build(BaseRspsMsg.BIZ_CODE_00001_FAILE,ex.getMessage());
        return baseRspsMsg.toJsonStr();
    }
}
