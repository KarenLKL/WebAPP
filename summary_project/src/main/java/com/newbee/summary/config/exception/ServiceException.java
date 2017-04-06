package com.newbee.summary.config.exception;

/**
 * Created by kl09 on 2017/4/1.
 */
public class ServiceException extends BaseException {
    public ServiceException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ServiceException(Throwable cause, String errorCode, String errorMsg) {
        super(cause, errorCode, errorMsg);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
