package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.User;
import com.service.LoginService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller  //注解为控制器
public class LoginController {

    @Autowired
    LoginService loginService;  //注入service层

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String get(ModelMap model){  //用来返回一个页面
        model.addAttribute("msg","请登录");
        return "login";  //返回指向login.jsp页面
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String post(User user, ModelMap model, HttpServletRequest request,
                       HttpServletResponse response){  //用来处理用户的登陆请求
        HttpSession session = request.getSession();
        if (loginService.login(user.getUserName(), user.getPassword())) {
            //add cookies
            Cookie userNameCookie = new Cookie("loginUserName", user.getUserName());
            Cookie passwordCookie = new Cookie("loginPassword", user.getPassword());
            userNameCookie.setMaxAge(30*60);
            userNameCookie.setPath("/");
            passwordCookie.setMaxAge(30*60);
            passwordCookie.setPath("/");
            response.addCookie(userNameCookie);
            response.addCookie(passwordCookie);

            session.setAttribute("user",user);
            return "login_success";  //登陆成功，跳转到login_success.jsp页面
        }
        else {
            model.addAttribute("msg","登录失败，请重新登录!");
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
        //delete cookies
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        request.getSession().invalidate();
        model.addAttribute("msg","用户已退出，请重新登录");
        return "login";
    }

    @RequestMapping(value = "/show")
    public String show() {
        return "show";
    }

}