package com.stackroute.productservice.repo;

import com.stackroute.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends MongoRepository<Product,String> {
 List<Product> findByProductNameContaining(String name);
 List<Product> findByProductCategory(String name);

 Optional<Product> findProductByProductId(String productId);
}
