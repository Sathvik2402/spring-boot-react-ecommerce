//package com.org.userservice.security;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.CorsRegistry;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
//@Configuration
//public class WebConfig implements WebFluxConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000","http://localhost:8086")  // Allow React frontend origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific methods
//                .allowCredentials(true)
//                .allowedHeaders("*");
//    }
//}
