package org.wang.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置拦截器信息配置
        // 1、获取远程应用的host信息：localhost:8080
        String origin = request.getHeader("Origin");
        // 2、设置这个host信息允许跨域
        response.setHeader("Access-Control-Allow-Origin", origin);
        // 3、这些请求类型都是允许跨域的
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 4、最大的链接保存时间是3600秒
        response.setHeader("Access-Control-Max-Age", "3600");
        // 5、允许使用session，默认跨域不允许使用session
         response.setHeader("Access-Control-Allow-Credentials", "true");
        // 6、跨域的时候请求头信息允许使用content-type
        response.setHeader("Access-Control-Allow-Headers", "content-type,Authorization");
        // 7.告知系统，请把这个头部数据携带给我
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        // 8、如果是预提交判断直接返回浏览器说，可以提交正常信息了
        if(request.getMethod().toLowerCase().equals("options")) {
            return false;
        }
        return true;
    }
}
