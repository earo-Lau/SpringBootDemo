package com.earo.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by lauearo on 04/05/2017.
 */
@Configuration
@EnableWebSocketMessageBroker   //enable STOMP protocol to send message via message broker
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {   //registry endpoint method
        stompEndpointRegistry.addEndpoint("/endpointEaro").withSockJS();    //registry an endpoint, and using sockjs protocol
    }

    public void configureMessageBroker(MessageBrokerRegistry registry){ //config message broker
        registry.enableSimpleBroker("/topic");   //registry a message broker
    }


}
