package com.newbee.summary.util;

import java.util.List;

/**
 * 响应数据个格式
 * Created by kl09 on 2017/4/1.
 */
public class ResponseData<T> {

    /**
     * 操作标识
     * 1：成功，0：失败
     */
    private int flag;

    /**
     * 结果提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private List<T> data;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
