package com.newbee.summary.config.exception;

/**
 * Created by kl09 on 2017/4/1.
 */
public class BaseException extends Exception {
    public String errorCode;
    public String errorMsg;

    public BaseException(String errorCode, String errorMsg) {
        super("errorCode:" + errorCode + ";errorMsg:" + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(Throwable cause, String errorCode, String errorMsg) {
        super("errorCode:" + errorCode + ";errorMsg:" + errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
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
}
