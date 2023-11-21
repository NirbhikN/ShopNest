package com.stackroute.orderservice.controller;



import com.stackroute.orderservice.model.Order;
import com.stackroute.orderservice.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.orderservice.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;


    private OrderRepo orderRepo;

    public ResponseEntity responseEntity;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/order")
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @PostMapping("/order")
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/order/{productId}")
    public Order editOrder(@PathVariable String productId, @RequestBody Order order){
        Order newOrder=orderService.editOrder(productId,order);
        return orderService.addOrder(newOrder);

    }

}
