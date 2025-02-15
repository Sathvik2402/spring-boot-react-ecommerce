package com.org.userservice.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class )
public ResponseEntity<String> handleAccessDeniedException(HttpClientErrorException e) {
    return new ResponseEntity<>("wrong credentials", HttpStatus.FORBIDDEN);
}
@ExceptionHandler(AuthenticationException.class )
public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return new ResponseEntity<>("Authentication Failed Wrong Credentials", HttpStatus.BAD_REQUEST);
}


}
