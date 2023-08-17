package org.wang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wang.interceptor.LogInterceptor;

import javax.annotation.Resource;


public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private LogInterceptor logInterceptor;

    @Resource
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }
}
