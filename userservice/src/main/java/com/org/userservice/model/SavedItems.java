package com.org.userservice.model;

import java.util.List;

public class SavedItems {
    private String name;
    List<Product> productsList;
    public SavedItems(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Saved{" +
                "name='" + name + '\'' +
                '}';
    }
}
