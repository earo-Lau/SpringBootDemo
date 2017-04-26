package com.test.springmvc4.web;

import com.test.springmvc4.domain.TestObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lauearo on 26/04/2017.
 */
@RestController     //declare rest controller
@RequestMapping("/rest")
public class DemoRESTController {

    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"})  //set response MINE Type to json
    public TestObj getJSON(TestObj obj) {
        return new TestObj(obj.getId(), obj.getName()); //return a object, will be serialize to json string
    }

    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"})    //set response MINE Type to XML
    public TestObj getXML(TestObj obj) {
        return new TestObj(obj.getId(), obj.getName()); //return an object, will be serialize to xml string
    }
}
