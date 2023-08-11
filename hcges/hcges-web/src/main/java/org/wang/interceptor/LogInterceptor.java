package org.wang.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.wang.model.Log;
import org.wang.service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userloginstate = session.getAttribute("userloginstate");
        System.out.println("___________________看看日志记录拦截器"+userloginstate);
//        String userId = (String) session.getAttribute("userId");
//        String url = request.getRequestURI();
//        String method = request.getMethod();
//        String params = request.getQueryString() != null ? request.getQueryString() : "";
//        String ip = request.getRemoteAddr();
//        String userAgent = request.getHeader("User-Agent");

//        OperationLog operationLog = new OperationLog();
//        operationLog.setUserId(userId);
//        operationLog.setUrl(url);
//        operationLog.setMethod(method);
//        operationLog.setParams(params);
//        operationLog.setIp(ip);
//        operationLog.setUserAgent(userAgent);
//        operationLogService.addOperationLog(operationLog);

//        Log log = new Log();
//        log.setID(userId);
//        log.setAction_time();
//        log.setOperator();
//        log.setAction_type();
//        log.setAction_content();

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if (response.getStatus() == HttpServletResponse.SC_OK) {
//            HttpSession session = request.getSession();
//            String userId = (String) session.getAttribute("userId");
//            String url = request.getRequestURI();
//            String method = request.getMethod();
//            String params = request.getQueryString() != null ? request.getQueryString() : "";
//            String ip = request.getRemoteAddr();
//            String userAgent = request.getHeader("User-Agent");
//
//            OperationLog operationLog = new OperationLog();
//            operationLog.setUserId(userId);
//            operationLog.setUrl(url);
//            operationLog.setMethod(method);
//            operationLog.setParams(params);
//            operationLog.setIp(ip);
//            operationLog.setUserAgent(userAgent);
//            operationLogService.addOperationLog(operationLog);
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
