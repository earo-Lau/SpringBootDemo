package com.test.springmvc4.web;

import com.test.springmvc4.domain.TestObj;
import com.test.springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lauearo on 01/05/2017.
 */
@Controller
public class NormalContorller {
    @Autowired
    DemoService demoService;

    @RequestMapping("/normal")
    public String normal(Model model) {
        model.addAttribute("msg", demoService.saySomething());

        return "page";
    }
}
