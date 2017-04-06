package com.newbee.summary.config.exception;

/**
 * 系统严重错误
 * Created by kl09 on 2017/4/5.
 */
public class SystemSeriouException extends RuntimeException {
    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 错误附近信息
     * 输入参数等信息
     */
    private String extraMsg;

    public SystemSeriouException(String errorCode, String errorMsg, String extraMsg) {
        super("errorCode:" + errorCode + ";errorMsg:" + errorMsg + ";extraMsg:" + extraMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.extraMsg = extraMsg;
    }

    public SystemSeriouException(String errorCode, String errorMsg, String extraMsg, Throwable cause) {
        super("errorCode:" + errorCode + ";errorMsg:" + errorMsg + ";extraMsg:" + extraMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.extraMsg = extraMsg;
    }

    @Override
    public String toString() {
        return "SystemSeriouException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", extraMsg='" + extraMsg + '\'' +
                '}';
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getExtraMsg() {
        return extraMsg;
    }

    public void setExtraMsg(String extraMsg) {
        this.extraMsg = extraMsg;
    }
}
