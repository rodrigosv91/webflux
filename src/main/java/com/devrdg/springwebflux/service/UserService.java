package com.devrdg.springwebflux.service;

import com.devrdg.springwebflux.model.User;
import com.devrdg.springwebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //Mono - 0 - 1 // gets a single stream
    //Flux - 0 - N // reactive sequence of items (multiple streams)

    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user).subscribe();
    }

    public Mono<User> updateUser(User user){
        return userRepository.findById(user.getId())
                .switchIfEmpty(Mono.error(new Exception("User not found")))
                .map(oldUser -> {
                    if(user.getName() != null) oldUser.setName(user.getName());
                    return oldUser;
                })
                .flatMap(userRepository::save);
    }

    public Mono<Void> deleteUser(Long id){
        return userRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new Exception("User not found")));
    }
}