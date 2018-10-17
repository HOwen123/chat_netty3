package com.cn.common.core.module;

import sun.plugin2.message.Serializer;

import java.io.Serializable;

public class Result<T extends Serializer> {

    /**
     * 结果码
     */
    private int resultCode;

    /**
     * 结果内容
     */
    private T content;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static <T extends Serializer> Result<T> SUCCESS(T content){
        Result<T> result = new Result<T>();
        result.resultCode = ResultCode.SUCCESS;
        result.content = content;
        return result;
    }

    public static <T extends Serializer> Result<T> SUCCESS(){
        Result<T> result = new Result<>();
        result.resultCode = ResultCode.SUCCESS;
        return result;
    }

    public static <T extends Serializer> Result<T> ERROR(int resultCode){
        Result<T> result = new Result<>();
        result.resultCode = resultCode;
        return result;
    }
}
