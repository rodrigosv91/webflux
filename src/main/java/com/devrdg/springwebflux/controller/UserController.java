package com.devrdg.springwebflux.controller;

import com.devrdg.springwebflux.model.User;
import com.devrdg.springwebflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<User> findAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public Mono<User> findUserById( @PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
