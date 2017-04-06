package com.newbee.summary.config.exception;

/**
 * Created by kl09 on 2017/4/1.
 */
public class DaoException extends BaseException {
    public DaoException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public DaoException(Throwable cause, String errorCode, String errorMsg) {
        super(cause, errorCode, errorMsg);
    }

    @Override
    public String toString() {
        return "DaoException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
