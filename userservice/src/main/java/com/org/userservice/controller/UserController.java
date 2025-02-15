package com.org.userservice.controller;


import com.org.userservice.model.*;
import com.org.userservice.repository.UserRepository;
import com.org.userservice.service.UserService;
import com.org.userservice.util.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.ReactiveSessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import org.springframework.web.server.session.InMemoryWebSessionStore;
import org.springframework.web.server.session.WebSessionStore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    @Autowired
    private UserService userService;


    private final WebSessionStore webSessionStore = new InMemoryWebSessionStore();

    @Autowired
    private ReactiveSessionRegistry sessionRegistry;


    private final ReactiveAuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    public UserController(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/users")
    public Flux<MyUser> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public Mono<MyUserDTO> getUser(ServerWebExchange exchange) {
        MyUserDTO dto = new MyUserDTO();

        Mono<MyUser> myuser = ReactiveSecurityContextHolder.getContext().map(SecurityContext -> {
                    Authentication authentication = SecurityContext.getAuthentication();
                    return (MyUser) authentication.getPrincipal();
                }
        );

        return myuser.map(myUser -> {
            dto.setSavedItems(myUser.getSavedItems());
            dto.setRole(myUser.getRole());
            dto.setUsername(myUser.getUsername());
            return dto;
        });

    }

    @PostMapping("/register")
    public Mono<MyUser> register(@RequestBody MyUser user) {
        return userService.addUser(user);
    }

    @GetMapping("/name")
    public Mono<String> getUserByName(String name) {
        return Mono.just("HEY from /name");
    }

//    @PostMapping("/signin")
//    public Mono<MyUserDTO> login
//            (@ModelAttribute LoginRequest loginRequest) throws AuthenticationException, AuthorizationDeniedException, HttpClientErrorException {
//
//        MyUserDTO myUserDTO=new MyUserDTO();
//        UsernamePasswordAuthenticationToken authenticationToken =
//        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//
//        return authenticationManager.authenticate(authenticationToken).flatMap(
//                authentication -> {
//                    return authentication.isAuthenticated()? Mono.just((MyUser)authentication.getPrincipal()).map(myUser ->
//                            {
//                                myUserDTO.setUsername(myUser.getUsername());
//                                myUserDTO.setSavedItems(myUser.getSavedItems());
//                                myUserDTO.setRole(myUser.getRole());
//                            return myUserDTO;
//                            }
//                            ) : Mono.empty();
//                }
//        );
//    }

    @PutMapping("/update")
    public Mono<MyUserDTO> addToCart(@RequestBody UpdateRequest updateRequest) {
        MyUserDTO dto = new MyUserDTO();
        Mono<MyUser> myuser = userRepository.findByUsername(updateRequest.getUsername()).flatMap(user -> {
                    if (user.getSavedItems() == null) {
                        user.setSavedItems(new ArrayList<>());
                    }
                    user.getSavedItems().add(updateRequest.getProduct());
                    return userRepository.save(user);
                })
                .switchIfEmpty(Mono.error(
                        new UsernameNotFoundException("User with name" + updateRequest.getUsername()+ " not found")
                ));

        return myuser.map(
                myUser -> {
                    dto.setUsername(myUser.getUsername());
                    dto.setSavedItems(myUser.getSavedItems());
                    dto.setRole(myUser.getRole());
                    return dto;
                }

        );

    }
}
