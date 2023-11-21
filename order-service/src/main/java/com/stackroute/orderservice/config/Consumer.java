package com.stackroute.orderservice.config;


import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.model.ProductRMQ1;
import com.stackroute.orderservice.repo.OrderRepo;
import com.stackroute.orderservice.service.OrderServiceImpl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
   private OrderServiceImpl orderService;

    @Autowired
    private OrderRepo order;

    @RabbitListener(queues = "order_queue")
    public void getUserRMQFromRabbitMq1(ProductRMQ1 productRMQ1){
        System.out.println("SellerId: "+ productRMQ1.getSellerId());
        System.out.println("ProductId: "+productRMQ1.getProductId());
//        System.out.println("Desc: "+productRMQ1.getProductDesc());
        Order or = new Order();
        or.setSellerId(productRMQ1.getSellerId());
        or.setProductId(productRMQ1.getProductId());
        or.setProductPrice(productRMQ1.getProductPrice());
        or.setProductDesc(productRMQ1.getProductDesc());
        or.setProductName(productRMQ1.getProductName());
        order.save(or);
        System.out.println(order.toString());
    }
}
