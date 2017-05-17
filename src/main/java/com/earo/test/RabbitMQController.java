package com.earo.test;

import com.earo.test.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lauearo on 17/05/2017.
 */
@RestController
public class RabbitMQController {
    @Autowired
    TopicService topicService;

    @RequestMapping("/publish")
    public String publish(String msg){
        topicService.publish(msg);

        return "msg published in thread " + Thread.currentThread().getId();
    }
}
