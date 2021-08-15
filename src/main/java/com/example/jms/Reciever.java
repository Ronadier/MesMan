package com.example.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import service.Message;

@Component
public class Reciever {

    @JmsListener(destination = "messages", containerFactory = "myFactory")
    public Message receiveMessage(Message message) {
        System.out.println("recieved "+ message.getId());
        return message;
    }
}