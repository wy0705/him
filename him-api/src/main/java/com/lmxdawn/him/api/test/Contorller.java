package com.lmxdawn.him.api.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class Contorller {
    @Autowired
    private UserDao dao;

    // 注册
    @RequestMapping("/reg")
    public ModelAndView register(UserBean userbean) {
        dao.add(userbean);
        ModelAndView mav = new ModelAndView("redirect:/login.jsp");
        return mav;
    }

    // 登陆
    /*@RequestMapping("/login")
    public ModelAndView login(UserBean userbean, HttpServletRequest request) {
        boolean flag =false;
        ModelAndView mav = new ModelAndView();
        flag = dao.login(userbean);
        if(flag == true){
            //登录成功，添加到session中
            request.getSession().setAttribute(Constants.SESSION_USER_BEAN, userBean2);
            mav.setViewName("/index");
        }
        else{
            //登录失败，向页面返回错误信息,,URL重写传参
            mav.setViewName("redirect:/login.jsp?status=1");
        }
        return mav;

    }
*/
    //登陆
    @RequestMapping("/login")
    public ModelAndView login(UserBean userBean, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if(userBean.getName() != "" && userBean.getPwd() != ""){
            UserBean userBean2 = dao.login(userBean);
            if(userBean2 != null){
                request.getSession().setAttribute(Constants.SESSION_USER_BEAN, userBean2);
                mav.setViewName("/index");
            }
            else {
                mav.setViewName("redirect:/login.jsp?status=1");
            }
        }
        else {
                mav.setViewName("redirect:/login.jsp?status=1");
        }
        return mav;
    }
}
