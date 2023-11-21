package com.stackroute.orderservice.service;

import com.stackroute.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.orderservice.repo.OrderRepo;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public Order addOrder(Order order) {
        return  orderRepo.save(order);
    }

    @Override
    public Order editOrder(String productId, Order order) {
        System.out.println(productId);

        Order newOrder = orderRepo.findOrderByProductId(productId);
        System.out.println(newOrder.toString());
        newOrder.setBuyerId(order.getBuyerId());
        return orderRepo.save(newOrder);

    }

}
