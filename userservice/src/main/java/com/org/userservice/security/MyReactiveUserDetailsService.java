package com.org.userservice.security;

import com.org.userservice.model.MyUser;
import com.org.userservice.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MyReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository; // Your reactive user repository (e.g., MongoDB repository)

    public MyReactiveUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        // Retrieve user from the reactive repository (e.g., MongoDB)
        return userRepository.findByUsername(username)
                .map(user -> new MyUser(user.getUsername(), user.getPassword(),user.getSavedItems(),user.getRole()
                ));// Assign roles to the user

//                .map(myUser -> User.withUsername(myUser.getUsername()).password(myUser.getPassword()).roles("USER").build());
    }
}