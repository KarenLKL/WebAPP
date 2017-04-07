package com.newbee.summary.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newbee.summary.constant.ExecutEnum;
import com.newbee.summary.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

/**
 * 拦截器配置
 * Created by kl09 on 2017/4/7.
 */
public class WebInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(WebInterceptor.class);

    //前置检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        ResponseData<Object> result = new ResponseData<>();
        if (parameterMap.size() < 1) {
            result.setFlag(Integer.parseInt(ExecutEnum.PARAMETER_NULL.getFlag()));
            result.setMessage(ExecutEnum.PARAMETER_NULL.getMessage());
            response.setCharacterEncoding("utf-8");
            PrintWriter pw = response.getWriter();
            pw.print(JSONObject.toJSONString(result));
            return false;
        }
        //身份校验
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            logger.info("参数:{}={}", entry.getKey(), entry.getValue());
        }
        return true;
    }

    /**
     * controller执行完成执行该方法
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
