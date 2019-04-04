package com.wwn.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Send {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("enjoy","hahhh");
    }
}
