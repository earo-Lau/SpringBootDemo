package com.test.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by lauearo on 28/04/2017.
 */
@Controller
public class SSEController {
    @RequestMapping(value = "/push", produces = {"text/event-stream"})
    //media type "text/event-stream", server SSE required
    public @ResponseBody
    String push() {
        Random random = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return "data: Testing 1,2,3 " + random.nextInt() + "\n\n";
    }
}
