package com.org.productservice.controller;


import com.org.productservice.entity.Product;
import com.org.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
//    @Autowired
//    private ProductRepository productRepository;

    @GetMapping("/all")
    public Flux<Product> getAllProducts() {
        return productService.getProducts();
    }
    @GetMapping("/id")
    public Mono<Product> getProductById(@RequestParam("id")String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/category")
    public Flux<Product> getProductsByCategory(@RequestParam("category")String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/create")
    public Mono<Product> createProduct(Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public Mono<Product> updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete")
    public Mono<Product> deleteProduct(String id) {
        return productService.deleteProduct(id);
    }

}
