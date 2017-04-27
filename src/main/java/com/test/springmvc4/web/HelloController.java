package com.test.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lauearo on 25/04/2017.
 */
@Controller //declare a controller
public class HelloController {
    //@RequestMapping("/index")   // using RequestMapping to map URL with method
    public String hello() {
        return "index"; //return the file name and mapping from ViewResolver
    }
}
