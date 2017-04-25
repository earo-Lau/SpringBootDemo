package com.test.springmvc4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by lauearo on 25/04/2017.
 */
public class WebInitializer implements WebApplicationInitializer { // using WebApplicationInitializer to launch Servlet 3.0 Container

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);  //relative servletContext with WebApplicationContext.

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  //register dispatcherServlet for Spring MVC
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
