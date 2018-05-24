package com.example.demo.domain;

import io.swagger.annotations.ApiModelProperty;

public class BaseResult<T> {
    @ApiModelProperty("返回状态码0-失败，1-成功")
    private int code;
    @ApiModelProperty("接口返回提示信息")
    private String message;
    @ApiModelProperty("接口返回数据")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
