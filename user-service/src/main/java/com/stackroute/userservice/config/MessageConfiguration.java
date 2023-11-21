package com.stackroute.userservice.config;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    public String exchangeName ="user_exchange";



    @Bean
    public DirectExchange directExchange(){
//        return new DirectExchange(exchangeName);
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Jackson2JsonMessageConverter prjsonMessageConverter(){return new Jackson2JsonMessageConverter();}

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(prjsonMessageConverter());
        return rabbitTemplate;
    }
}

