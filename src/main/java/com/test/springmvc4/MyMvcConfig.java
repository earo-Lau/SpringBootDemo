package com.test.springmvc4;

import com.test.springmvc4.interceptor.DemoInterceptor;
import com.test.springmvc4.messageconverter.CustomMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;


/**
 * Created by lauearo on 25/04/2017.
 */
@Configuration
@EnableWebMvc   //Enable Spring MVC
@ComponentScan("com.test.springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {  //inherit WebMvcConfigurerAdapter, which config for Spring MVC
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);

        return viewResolver;
    }

    @Bean
    public HandlerInterceptor getDemoInterceptor() {
        return new DemoInterceptor();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);

        return multipartResolver;
    }

    @Bean
    public CustomMessageConverter converter() {
        return new CustomMessageConverter();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {  //add extend message converter
        converters.add(converter());
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getDemoInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");   //addResourceHandler: Resource path exprot to WEB-INF/; addResourceLocations: resource location in src.
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/index").setViewName("/index"); //using ViewController to map path
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false); //allow '.' as a param value in url
    }
}
