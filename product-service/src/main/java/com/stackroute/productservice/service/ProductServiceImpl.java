package com.stackroute.productservice.service;

import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo){
        this.productRepo=productRepo;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }


    @Override
    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }

    @Override
    public Product editProduct(String id,Product product) {
        Product updatedProduct = productRepo.findById(id).get();
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductPrice(product.getProductPrice());
        updatedProduct.setProductDesc(product.getProductDesc());
        updatedProduct.setProductImgUrl(product.getProductImgUrl());
//        updatedProduct.setProductImgUrl(product.getProductImgUrl());
//        updatedProduct.setProductLocation(product.getProductLocation());
        return productRepo.save(updatedProduct);
    }

    @Override
    public List<Product> getByCategories(String categories) {
        return this.productRepo.findByProductCategory(categories);
    }

    @Override
    public List<Product> searchProduct(String name) {
        List<Product> products = this.productRepo.findByProductNameContaining(name);
        return products;
    }

    @Override
    public void deleteProduct(String productId) {
        productRepo.deleteById(productId);

    }

    @Override
    public Boolean statusChange(String productId){
        Optional<Product> optionalProduct = productRepo.findProductByProductId(productId);
            Product product = optionalProduct.get();
            product.setProductStatus("Sold");
            productRepo.save(product);
            return true;
    }
}
