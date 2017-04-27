package com.test.springmvc4.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lauearo on 26/04/2017.
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {    //inherit HandlerInterceptorAdapter to create custom interceptor
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {   //trigger before request process
//        super.preHandle(request, response, handler);
        long startTimeStamp = System.currentTimeMillis();
        request.setAttribute("startTime", startTimeStamp);

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  //trigger after request process
//        super.postHandle(request, response, handler, modelAndView);
        long startTimeStamp = (long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTimeStamp = System.currentTimeMillis();
        System.out.println("Process time of current request: " + (endTimeStamp - startTimeStamp) + "ms");

        request.setAttribute("handlingTime", endTimeStamp - startTimeStamp);
    }
}
