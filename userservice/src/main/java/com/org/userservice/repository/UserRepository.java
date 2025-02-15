package com.org.userservice.repository;

import com.org.userservice.model.MyUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<MyUser,String> {
    Mono<MyUser> findByUsername(String username);
}
