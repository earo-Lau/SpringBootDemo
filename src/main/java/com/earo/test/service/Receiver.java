package com.earo.test.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by lauearo on 17/05/2017.
 */
@Component
public class Receiver {
    @RabbitListener(queues = { "demoQueue" })
    public void ReceiveMessage(String msg){
        System.out.println("Received message <" + msg + "> from \"demoQueue\" in thread " + Thread.currentThread().getId());
    }
}
