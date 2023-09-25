package com.webflux3.controller;

import com.webflux3.model.User;
import com.webflux3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId){
        Mono<User> user = userService.findById(userId);
        return user.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //alternativa
//    @GetMapping("/{userId}")
//    public Mono<User> getUserById(@PathVariable String userId) {
//        return userService.findById(userId)
//                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
//    }

    @PutMapping("/{idUser}")
    public Mono<ResponseEntity<User>> updateUserById(@PathVariable String idUser, @RequestBody User user){
        return userService.updateUser(idUser,user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String userId){
        return userService.deleteUser(userId)
                .map(response -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    //alternativa
//    @DeleteMapping("/{userId}")
//    public Mono<ResponseEntity<Void>> deleteUserById2(@PathVariable String userId) {
//        return userService.deleteUser(userId)
//                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
//                .onErrorResume(Exception.class, e -> Mono.just(ResponseEntity.notFound().build()));
//    }

    @GetMapping("/search")
    public Flux<User> searchUsers(@RequestParam("name") String name){
        return userService.fetchUsers(name);
    }
}
