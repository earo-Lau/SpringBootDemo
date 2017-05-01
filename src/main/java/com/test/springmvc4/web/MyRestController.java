package com.test.springmvc4.web;

import com.test.springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lauearo on 01/05/2017.
 */
@Controller
public class MyRestController {
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest", produces = {"text/plain;charset=UTF-8"})
    public @ResponseBody
    String testRest() {
        return demoService.saySomething();
    }
}
