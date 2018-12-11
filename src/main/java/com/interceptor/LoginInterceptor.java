package com.interceptor;

import com.domain.User;
import com.service.LoginService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    LoginService loginService;  //注入service层
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("login.do")>=0){
            return true;
        }
//        //获取Session
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        if(user != null){
//            return true;
//        }
//        //不符合条件的，跳转到登录界面
//        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
//        return false;

        String loginCookieUserName = "";
        String loginCookiePassword = "";
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            return false;
        }
        else {
            for (Cookie cookie : cookies) {
                if ("loginUserName".equals(cookie.getName())) {
                    loginCookieUserName = cookie.getValue();
                }
                if ("loginPassword".equals(cookie.getName())) {
                    loginCookiePassword = cookie.getValue();
                }
            }
            if (loginService.login(loginCookieUserName, loginCookiePassword)) {
                //request.getRequestDispatcher("/WEB-INF/view/login_success.jsp").forward(request, response);
                return true;
            }
            else {
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                return false;
            }
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc) throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

}
