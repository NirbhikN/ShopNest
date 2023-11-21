package com.stackroute.userservice.config;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.rabbitmq.model.UserRMQ;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate,DirectExchange directExchange){
        super();
        this.rabbitTemplate=rabbitTemplate;
        this.directExchange=directExchange;
    }

    public void sendMessageToRabbitMq(UserRMQ userRMQ)
    {
        rabbitTemplate.convertAndSend(directExchange.getName(),"user_routing", userRMQ);
    }

//    public void sendMessageToRabbitMq2(ProductRMQ productRMQ)
//    {
//        rabbitTemplate.convertAndSend(directExchange.getName(),"product_routing", productRMQ);
//    }
}
