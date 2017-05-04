package com.earo.test;

import com.earo.test.model.ClientMessage;
import com.earo.test.model.ServerMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by lauearo on 04/05/2017.
 * Websocket Demo
 */
@Controller
public class WebSocketController {
    @MessageMapping("/welcome") //map url
    @SendTo("/topic/getResponse")   //Server will send message to client which subscript this url
    public ServerMessage say(ClientMessage message) throws Exception{
        Thread.sleep(3000);
        return new ServerMessage("Welcome, " + message.getName() + "!");
    }
}
