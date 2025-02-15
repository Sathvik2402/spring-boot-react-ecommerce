package com.org.productservice.repository;

import com.org.productservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
public Flux<Product> findByCategory(String category);
}
