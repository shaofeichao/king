package com.rest.rest_server.pub.exception.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "基础响应实体",description = "基础响应实体")
public class BaseRspsMsg<T> extends JsonObj {

    public static final String BIZ_CODE_00000_SUCCESS = "00000";
    public static final String BIZ_CODE_00001_FAILE = "00001";
    public static final String BIZ_CODE_00002_THROW_EXCEPTION = "00002";
    public static final String BIZ_CODE_00003_PARAMETER_ERROR = "00003";

    // 响应状态码
    @ApiModelProperty(value = "响应状态码")
    private String bizCode;

    // 响应消息，描述
    @ApiModelProperty(value = "响应消息，描述")
    private String bizDesc;

    // 响应中的数据
    @ApiModelProperty(value = "响应数据,可为空")
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
