package com.org.productservice.service;

import com.org.productservice.entity.Product;
import com.org.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getProducts() {
        return productRepository.findAll();

    }

    public Mono<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> addProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> deleteProduct(String id) {
        return productRepository.findById(id);
    }

}
