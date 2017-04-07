package com.newbee.summary.config.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.newbee.summary.util.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**记录请求参数、请求地址和相应结果切面等信息
 * Created by kl09 on 2017/4/1.
 */
@Aspect
@Configuration
public class ParamLogAspect {
    private final Logger logger= LoggerFactory.getLogger(ParamLogAspect.class);

    //定义切点pointcut
    @Pointcut("execution(* com.newbee.summary.controller..*(..))")
    public void parameterService(){

    }

    @Around("parameterService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();
        // 获取请求uri、方法、类名等信息
        String remoteAddress = request.getRemoteHost() + ":" + request.getRemotePort();
        String uri = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getSimpleName();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取参数名称，参数值
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameterValues = pjp.getArgs();
        StringBuilder paramBuilder = new StringBuilder();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                paramBuilder
                        .append("[")
                        .append(parameterNames[i])
                        .append("=").append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat))
                        .append("]");
            }
        }
        // 序列化
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            logger.error("request error! request ip:{}, request url:{}, request Controller:{}, request method:{}, request param:{}",
                    remoteAddress, uri, clazzName, methodName, paramBuilder.toString());
            throw throwable;
        }
        try {
            String jsonString = "";
            if (result != null) {
                if (result instanceof ResponseData) {
                    jsonString = JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
                } else {
                    jsonString = String.valueOf(result);
                }
            }
            logger.info("request complete! request ip:{}, request url:{}, request Controller：{}, request method:{}, request param:{}, response data:{}",
                    remoteAddress, uri, clazzName, methodName, paramBuilder.toString(), jsonString);
        } catch (Exception e) {
            logger.error("json deserializer exception! error info:{}", e);
        }
        return result;
    }
}
