package com.test.springmvc4.web;

import com.test.springmvc4.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by lauearo on 28/04/2017.
 * Async task, by take a DeferredResult obj from another thread
 */
@Controller
public class AsyncController {
    @Autowired  //dependency injection
            PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        return pushService.getAsyncUpdate();
    }
}
