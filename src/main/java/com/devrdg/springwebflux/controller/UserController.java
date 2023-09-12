package com.devrdg.springwebflux.controller;

import com.devrdg.springwebflux.model.Users;
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
    public Flux<Users> findAllUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/users_stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Users> findAllUsersWithDelay(){
        return userService.getUsersWithDelay();
    }

    @GetMapping("/user/{id}")
    public Mono<Users> findUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody Users user){
        userService.addUser(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Users> updateUser(@RequestBody Users user){
        return userService.updateUser(user);
    }
}
