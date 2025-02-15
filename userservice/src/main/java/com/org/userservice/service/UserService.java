package com.org.userservice.service;

import com.org.userservice.model.MyUser;
import com.org.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public Flux<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<MyUser> getUserById(String id) {
        return userRepository.findById(id);
    }
    public Mono<MyUser> addUser(MyUser user) {
        return userRepository.save(user);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
