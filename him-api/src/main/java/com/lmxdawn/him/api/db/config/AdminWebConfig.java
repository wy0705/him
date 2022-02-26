package com.lmxdawn.him.api.db.config;

import com.lmxdawn.him.api.db.intercetpor.LoginIntercetpor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
    1.编写一个拦截器实现HandlerInterceptor接口
    2.将拦截器注册到容器中（实现WebMvcConfigurer）
    3.指定拦截规则
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor loginIntercetpor = new LoginIntercetpor();
        System.out.println(loginIntercetpor);
        System.out.println("--------------------------------------");
        registry.addInterceptor(loginIntercetpor)
                .addPathPatterns("/**")//拦资源截包括拦截静态
                .excludePathPatterns("/","/css/**","/fonts/**","/images/**","/js/**","/test","/Register","/Mapssss");//取消拦截静态资源
    }
}
