package com.org.userservice.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

@Component
public class MyReactiveAuthenticationManager implements ReactiveAuthenticationManager {


    private ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public MyReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws HttpClientErrorException {
        // Retrieve the username and password from the authentication token
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Use ReactiveUserDetailsService to load user details asynchronously
        return userDetailsService.findByUsername(username)
                .flatMap(userDetails -> {
                    //hashing the password from DB
                    String encodedPassword = passwordEncoder.encode(userDetails.getPassword());

                    // Check if the password matches
                    if (passwordEncoder.matches(password, encodedPassword)) {
                        // Return an authenticated token if credentials are valid
                        return Mono.just(new UsernamePasswordAuthenticationToken(
                                userDetails, password, userDetails.getAuthorities()));
                    } else {
                        // If credentials are invalid, return a failed authentication
                        return Mono.error(new BadCredentialsException("Invalid credentials"));
                    }
                });
    }

}
