package com.earo.test;

import com.earo.test.model.ClientMessage;
import com.earo.test.model.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by lauearo on 04/05/2017.
 * Websocket Demo
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messageTemplate;  //object to send message to client

    @MessageMapping("/welcome") //map url
    @SendTo("/topic/getResponse")   //Server will send message to client which subscript this url
    public ServerMessage say(ClientMessage message) throws Exception{
        Thread.sleep(3000);
        return new ServerMessage("Welcome, " + message.getName() + "!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg){    //principal contains current user info
        if(principal.getName().equals("earo")){
            messageTemplate.convertAndSendToUser("sakura"   //send message to specific user
                    , "/queue/notifications"    //the url subscript in client
                    , principal.getName() + "-send: " + msg);   //message send to client
        } else{
            messageTemplate.convertAndSendToUser("earo"
                    , "/queue/notifications"
                    , principal.getName() + "-send: "+ msg);
        }
    }
}
