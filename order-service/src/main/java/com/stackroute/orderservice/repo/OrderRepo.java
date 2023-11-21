package com.stackroute.orderservice.repo;

import com.stackroute.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends MongoRepository<Order,Integer>{
    Order findOrderByProductId(String productId);
}
