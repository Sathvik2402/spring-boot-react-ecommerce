package com.org.userservice.util;

import com.org.userservice.model.MyUser;
import com.org.userservice.model.MyUserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

public class AuthenticatedUser {


    public static Mono<MyUserDTO> getCurrentUser(){
        MyUserDTO dto = new MyUserDTO();
        Mono<MyUser> myuser= ReactiveSecurityContextHolder.getContext().map(SecurityContext ->{
                    Authentication authentication = SecurityContext.getAuthentication();
                    return (MyUser)authentication.getPrincipal();
                }
        );

        return myuser.map(myUser -> {
            dto.setSavedItems(myUser.getSavedItems());
            dto.setRole(myUser.getRole());
            dto.setUsername(myUser.getUsername());
            return dto;
        });
    }
}
