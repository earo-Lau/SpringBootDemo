package com.earo.test.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

/**
 * Created by lauearo on 17/05/2017.
 */
@Service
public class TopicService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public Queue demoQueue(){
        return new Queue("demoQueue");
    }

    public void publish(String msg){
        rabbitTemplate.convertAndSend("demoQueue", msg);
    }

}
