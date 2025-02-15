package com.org.userservice.model;

public class UpdateRequest {
    Product product;
    String username;

    public UpdateRequest(Product product, String username) {
        this.product = product;
        this.username = username;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "product=" + product +
                ", user=" + username +
                '}';
    }
}
