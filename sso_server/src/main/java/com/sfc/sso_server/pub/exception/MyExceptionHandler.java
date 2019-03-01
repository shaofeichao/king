package com.sfc.sso_server.pub.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义捕获异常抛出
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ExceptionMessage.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerMyException(ExceptionMessage ex) {
        log.error(" error MyExceptionHandler checkOrder handlerMyException ：" + ex.getMessage());
        BaseRspsMsg baseRspsMsg = BaseRspsMsg.build(BaseRspsMsg.BIZ_CODE_00001_FAILE,ex.getMessage());
        return baseRspsMsg.toJsonStr();
    }
}
