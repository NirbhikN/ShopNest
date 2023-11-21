package com.stackroute.productservice.config;

import com.stackroute.productservice.rabbitmq.model.ProductRMQ;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange){
        super();
        this.rabbitTemplate=rabbitTemplate;
        this.directExchange=directExchange;
    }

    public void sendMessageToRabbitMq(ProductRMQ productRMQ)
    {
        rabbitTemplate.convertAndSend(directExchange.getName(),"product_routing", productRMQ);
        rabbitTemplate.convertAndSend(directExchange.getName(),"order_routing", productRMQ);
        rabbitTemplate.convertAndSend(directExchange.getName(),"chat_routing", productRMQ);
    }

}
