package com.stackroute.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.productservice.config.Producer;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.rabbitmq.model.ProductRMQ;
import com.stackroute.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private ResponseEntity responseEntity;

    @Autowired
    private Producer producer;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping(value = {"products"})
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws IOException {
        ProductRMQ productRMQ = new ProductRMQ();
        productRMQ.setSellerId(product.getSellerId());
        productRMQ.setProductId(product.getProductId());
        productRMQ.setProductName(product.getProductName());
        productRMQ.setProductPrice(product.getProductPrice());
        productRMQ.setProductDesc(product.getProductDesc());
        productRMQ.setSlots(product.getSlots());
        producer.sendMessageToRabbitMq(productRMQ);
        System.out.println(productRMQ);
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);

    }
    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products =  productService.getAllProduct();
        if(!products.isEmpty()) {
            return new ResponseEntity<List<Product>>(
                    products,
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/{id}")
    Optional<Product> getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }


    @PutMapping("/products/{id}/edit")
    Product editProduct(@PathVariable String id, @RequestBody Product product){
        Product updatedProduct= productService.editProduct(id,product);
        return productService.addProduct(updatedProduct);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id){
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()) {
            try {
                productService.deleteProduct(id);
                return new ResponseEntity<Void>(
                        HttpStatus.OK);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Void>(

                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/search/{name}")
    public ResponseEntity<?> searchProduct(@PathVariable  String name){
        List<Product> product = this.productService.searchProduct(name);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/products/categories/{category}")
    public ResponseEntity<?> getCategories(@PathVariable  String category){
        List<Product> product = this.productService.getByCategories(category) ;
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public boolean editProduct(@PathVariable String id){
        return productService.statusChange(id);
    }
}
