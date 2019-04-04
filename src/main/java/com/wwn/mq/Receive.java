package com.wwn.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "enjoy")
public class Receive {

    @RabbitHandler
    public void process(String msg){
        System.out.println(msg);
    }
}
