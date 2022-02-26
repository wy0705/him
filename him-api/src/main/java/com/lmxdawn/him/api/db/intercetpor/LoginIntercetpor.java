package com.lmxdawn.him.api.db.intercetpor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginIntercetpor implements HandlerInterceptor {
    @Override

    /*
    目标方法处理之前
    1.配置好拦截器要拦击那些请求
    2.把这些配置放到容器中
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录之前验证
        HttpSession session = request.getSession();
        Object object = session.getAttribute("loginUser");
        if (object != null)
        {
            return true;
        }else {
            session.setAttribute("msg","请先登录!");
            System.out.println("123123123");
            response.sendRedirect("/");
            return false;
        }
    }
    /*
    目标方法执行之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /*
    页面渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
