package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getAllProduct();
    public void deleteProduct(String productId);
    public Optional<Product> getProductById(String id);
    Product editProduct(String id,Product product);
    public List<Product> getByCategories(String categories);
    List<Product> searchProduct(String name);
    Boolean statusChange(String productId);

}


