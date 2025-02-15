package com.org.userservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class MyUserDTO {
    String id;
    String username;
    String role;
    List<Product> savedItems;
public MyUserDTO() {}

    public MyUserDTO(String username,List<Product> savedItems, String role) {
        this.id = id;
        this.username = username;

        this.savedItems = savedItems;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public List<GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Product> getSavedItems() {
        return savedItems;
    }

    public void setSavedItems(List<Product> savedItems) {
        this.savedItems = savedItems;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + '\'' +
                ", role='" + role + '\'' +
                ", savedItems=" + savedItems +
                '}';
    }

}
