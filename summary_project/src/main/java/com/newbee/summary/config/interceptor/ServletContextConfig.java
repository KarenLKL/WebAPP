package com.newbee.summary.config.interceptor;

import com.newbee.summary.SummaryApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by kl09 on 2017/4/7.
 */
@Configuration
@ComponentScan(basePackageClasses = SummaryApplication.class, useDefaultFilters = true)
public class ServletContextConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //对所有请求都进行拦截,进行身份认证
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/*");
    }

}
