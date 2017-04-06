package com.newbee.summary.config.interceptor;

import com.newbee.summary.config.exception.DaoException;
import com.newbee.summary.config.exception.ServiceException;
import com.newbee.summary.config.exception.SystemSeriouException;
import com.newbee.summary.constant.ExecutEnum;
import com.newbee.summary.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created by kl09 on 2017/4/1.
 */
@ControllerAdvice
public class DefaultGlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(DefaultGlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData handle(Exception exception) {
        ResponseData responseData = new ResponseData();
        if (exception instanceof SystemSeriouException) {
            // 系统严重异常
            SystemSeriouException systemSeriouException = (SystemSeriouException) exception;
            logger.error("系统发生严重异常！异常类：[" + systemSeriouException.toString() +"], 异常信息:"+ exception);
            // 创建线程异步，异常池处理逻辑
            /*ThreadPoolExecutorSingleton.getInstance().execute(()->{
                exceptionPoolAExecutor.push2ExceptionMap(systemSeriouException);
            });*/
        } else if (exception instanceof ServiceException) {
            // 业务异常
            ServiceException businessException = (ServiceException) exception;
            logger.error("系统发生业务异常！异常类：[" + businessException.toString() +"], 异常信息:"+ exception);
        }else if(exception instanceof DaoException){
            // Dao异常
            DaoException daoException = (DaoException) exception;
            logger.error("系统发生Dao异常！异常类：[" + daoException.toString() +"], 异常信息:"+ exception);
            // 创建线程异步，异常池处理逻辑
            /*ThreadPoolExecutorSingleton.getInstance().execute(()->{
                exceptionPoolAExecutor.push2ExceptionMap(systemSeriouException);
            });*/
        } else {
            // 其余类型异常
            logger.error("系统发生未知类型异常！异常类：[{}], 异常信息:{}", exception.getClass(), exception);
        }
        responseData.setFlag(Integer.parseInt(ExecutEnum.EXECUTIVE_FAIL.getFlag()));
        responseData.setMessage("系统异常！");
        return responseData;
    }
}
