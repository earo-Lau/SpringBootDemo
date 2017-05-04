package com.earo.test.model;

/**
 * Created by lauearo on 04/05/2017.
 */
public class ServerMessage {
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public ServerMessage(String responseMessage) {

        this.responseMessage = responseMessage;
    }
}
