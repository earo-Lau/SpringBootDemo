package com.earo.test;

import com.earo.starter.hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lauearo on 02/05/2017.
 */
@RestController
@SpringBootApplication
public class HelloServiceApplication {
    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public String index(){
        return helloService.SaySomething();
    }

    public static void main(String[] args){
        new SpringApplicationBuilder(HelloServiceApplication.class)
                .run(args);
    }
}
