package com.org.userservice.security;


import com.org.userservice.service.MyReactiveAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.session.InMemoryReactiveSessionRegistry;
import org.springframework.security.core.session.ReactiveSessionRegistry;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    SecurityConfig(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public ReactiveAuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        return new MyReactiveAuthenticationManager(userDetailsService, passwordEncoder);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, ReactiveAuthenticationManager authenticationManager) throws Exception {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
                new SecurityContextServerLogoutHandler(), new WebSessionServerLogoutHandler()
        );
        http
                .cors(Customizer.withDefaults())
                .authorizeExchange(exchange -> {
                    exchange.pathMatchers("/signin", "/signout", "/login").permitAll();
                    exchange.pathMatchers("/register", "/login").hasAnyRole("USER")
                            .anyExchange().authenticated()

                    ;
                })
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(formLoginSpec ->
                        formLoginSpec

                                .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                                    webFilterExchange
                                            .getExchange()
                                            .getResponse()
                                            .setStatusCode(HttpStatus.OK);
                                    String username = authentication.getPrincipal().toString();
                                    String responseMessage = "{\"message\":\"Login successful\"}";
                                    return webFilterExchange.getExchange().getResponse()
                                            .writeWith(Mono.just(webFilterExchange.getExchange()
                                                    .getResponse()
                                                    .bufferFactory().wrap(responseMessage.getBytes())
                                            ));
                                })
                                .authenticationFailureHandler((webFilterExchange, exception) -> {
                                    webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                    return Mono.empty();
                                })
                )
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .logout(logoutSpec ->
                        logoutSpec.logoutHandler(logoutHandler)
                )
        ;
        return http.build();
    }

    @Bean
    public ReactiveSessionRegistry sessionRegistry() {
        return new InMemoryReactiveSessionRegistry();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST","PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
