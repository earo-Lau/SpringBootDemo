package com.test.springmvc4.web;

import com.test.springmvc4.domain.TestObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lauearo on 25/04/2017.
 */
@Controller
@RequestMapping("/anno")    //mapping controller with Path "/anno"
public class DemoAnnoController {
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    //default access to controller, produces to declare the response MINE type
    public @ResponseBody
    String index(HttpServletRequest request) { //@ResponseBody let the value return in the reponse's body
        return "url: " + request.getRequestURL() + " can access";
    }

    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
    //mapping path "/anno/pathvar/xx" to method
    public @ResponseBody
    String demoPathVar(@PathVariable String str, HttpServletRequest request) { //@PathVariable to declare this arg is mapping from path
        return "url: " + request.getRequestURL() + " can access, str: " + str;
    }

    @RequestMapping(value = "/requestParam", produces = "text/plain; charset=UTF-8")
    //mapping path "/anno/requestParam?id=0"
    public @ResponseBody
    String passRequestParam(Long id, HttpServletRequest request) {  //@id get from the url's param
        return "url: " + request.getRequestURL() + " can access, id: " + id;
    }

    @RequestMapping(value = "/requestObj", produces = "text/plain; charset=UTF-8")
    //mapping path "/anno/requestObj?id=1&name=abc"
    @ResponseBody   //@RequestBody could be use in method
    public String passObj(TestObj obj, HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access, obj id: " + obj.getId() + ", obj name: " + obj.getName();
    }

    @RequestMapping(value = {"/name1", "/name2"}, produces = "text/plain;charset=UTF-8")
    //mapping multiple path "/anno/name1", "/anno/name2"
    public @ResponseBody
    String remove(HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access";
    }
}
