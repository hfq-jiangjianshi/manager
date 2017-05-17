package com.hfq.house.manager.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 请求返回信息
 * 
 * @author jjs 2016年10月14日 上午11:39:35
 */
public class RespMsg<T> {

    private int    status; // 0： 成功  1：失败   -1 | 500：服务器失败   
    private String msg;
    private T      data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
    	
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }

}
