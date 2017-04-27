package com.test.springmvc4.web;

import com.test.springmvc4.domain.TestObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lauearo on 26/04/2017.
 */
@Controller
public class AdviceController {
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, TestObj obj) {
        throw new IllegalArgumentException("Sorry, parameter error/" + "来自@ModelAttribute: " + msg);
    }
}
