package com.stackroute.orderservice.service;

import com.stackroute.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrder();

    public Order addOrder(Order order);

    public Order editOrder(String productId,Order order);


}



