package com.qiang.common.base;

/**
 * Created by xieqiang_daye on 2018/2/4.
 */
/***
 *统一返回结果类
 */
public class BaseResult {
    /**
     * 状态码：1成功，其他为失败
     * */
    private int code;

    /**
     *成功为success，其他为失败
     * */
    private String success;
    /**
     * 数据结果类
     * */
    private Object data;

    public BaseResult(int code, String success, Object data) {
        this.code = code;
        this.success = success;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
