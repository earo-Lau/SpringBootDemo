package com.test.springmvc4.web;

import com.test.springmvc4.domain.TestObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lauearo on 27/04/2017.
 */
@Controller
public class ConverterController {
    @RequestMapping(value = "/convert", produces = {"application/x-lauearo"})
    //media type shall be the same with our custom converter
    public @ResponseBody
    TestObj convert(@RequestBody TestObj obj) {
        return obj;
    }
}
