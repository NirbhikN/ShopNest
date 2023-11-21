package com.stackroute.slotservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
public String exchaneName="product_exchange";
public String registerQueue="product_queue";

@Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){return new Jackson2JsonMessageConverter();}

    @Bean
    public DirectExchange directExchange(){
    return new DirectExchange(exchaneName);
    }

    @Bean
    public Queue registerQueue(){
    return new Queue(registerQueue,false);
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange directExchange){
    return BindingBuilder.bind(registerQueue()).to(directExchange).with("product_routing");
    }
}
