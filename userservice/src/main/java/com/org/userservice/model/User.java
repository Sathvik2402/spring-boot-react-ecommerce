//package com.org.userservice.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.List;
//
//@Document(collection = "users")
//public class User {
//
//    private String id;
//    private String username;
//    private String firstname;
//    private String lastname;
//    private String password;
//    private String email;
//    private String phone;
//    private String image;
//    private List<String> address;
//    private String gender;
//    private String birthday;
//    private List<String> orderhistory;
//    private List<Cart> cart;
//    private List<String> transactions;
//    private List<String> wishlist;
//
//    public User(String id, String username, String password, String email, String phone, String image, List<String> address, String gender, String birthday, List<String> orderhistory, List<Cart> cart, List<String> transactions, List<String> wishlist) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.image = image;
//        this.address = address;
//        this.gender = gender;
//        this.birthday = birthday;
//        this.orderhistory = orderhistory;
//        this.cart = cart;
//        this.transactions = transactions;
//        this.wishlist = wishlist;
//    }
//
//    public User() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public List<String> getAddress() {
//        return address;
//    }
//
//    public void setAddress(List<String> address) {
//        this.address = address;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(String birthday) {
//        this.birthday = birthday;
//    }
//
//    public List<String> getOrderhistory() {
//        return orderhistory;
//    }
//
//    public void setOrderhistory(List<String> orderhistory) {
//        this.orderhistory = orderhistory;
//    }
//
//    public List<Cart> getCart() {
//        return cart;
//    }
//
//    public void setCart(List<Cart> cart) {
//        this.cart = cart;
//    }
//
//    public List<String> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<String> transactions) {
//        this.transactions = transactions;
//    }
//
//    public List<String> getWishlist() {
//        return wishlist;
//    }
//
//    public void setWishlist(List<String> wishlist) {
//        this.wishlist = wishlist;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id='" + id + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", image='" + image + '\'' +
//                ", address=" + address +
//                ", gender='" + gender + '\'' +
//                ", birthday='" + birthday + '\'' +
//                ", orderhistory=" + orderhistory +
//                ", cart=" + cart +
//                ", transactions=" + transactions +
//                ", wishlist=" + wishlist +
//                '}';
//    }
//}
