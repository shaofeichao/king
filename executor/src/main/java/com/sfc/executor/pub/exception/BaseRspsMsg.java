package com.sfc.executor.pub.exception;

import com.sfc.executor.pub.utils.JsonObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseRspsMsg<T> extends JsonObj {

    public static final String BIZ_CODE_00000_SUCCESS = "00000";
    public static final String BIZ_CODE_00001_FAILE = "00001";
    public static final String BIZ_CODE_00002_THROW_EXCEPTION = "00002";
    public static final String BIZ_CODE_00003_PARAMETER_ERROR = "00003";

    /** 订购状态码 start  **/


    /** 订购状态码 end  **/


    /** 产品状态码 start  **/


    /** 产品状态码 end  **/


    /** 订单状态码 start  **/


    /** 订单状态码 end  **/


    /** 客户状态码 start  **/


    /** 客户状态码 end  **/


    // 响应状态码
    private String bizCode;

    // 响应消息，描述
    private String bizDesc;

    // 响应中的数据
    private T data;


    public BaseRspsMsg() {

    }

    public BaseRspsMsg(String bizCode, String bizDesc) {
        super();
        this.bizCode = bizCode;
        this.bizDesc = bizDesc;
    }

    public BaseRspsMsg(T data) {
        this.bizCode = BIZ_CODE_00000_SUCCESS;
        this.bizDesc = "OK";
        this.data = data;
    }

    public BaseRspsMsg(String bizCode, String bizDesc, T data) {
        this.bizCode = bizCode;
        this.bizDesc = bizDesc;
        this.data = data;
    }


    public static BaseRspsMsg ok(Object data) {
        return new BaseRspsMsg(data);
    }

    public static BaseRspsMsg ok() {
        return new BaseRspsMsg(null);
    }

    public static BaseRspsMsg build(String bizCode, String bizDesc) {
        return new BaseRspsMsg(bizCode, bizDesc, null);
    }
    
    public static <T>BaseRspsMsg build(String bizCode, String bizDesc,T data) {
        return new BaseRspsMsg(bizCode, bizDesc, data);
    }
    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
