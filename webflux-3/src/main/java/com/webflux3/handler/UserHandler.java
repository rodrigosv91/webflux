package com.webflux3.handler;

import com.webflux3.model.User;
import com.webflux3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> getAllUsers(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getAllUsers(), User.class);
        // É necessário informar a classe para que Spring WebFlux saiba como converter os objetos em JSON corretamente
        // vide serialização
    }

    public Mono<ServerResponse> getUserById(ServerRequest request){
//        return userService
//                .findById(request.pathVariable("userId"))
//                .flatMap(user -> ServerResponse
//                        .ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(user, User.class)
//                )
//                .switchIfEmpty(ServerResponse.notFound().build());

        String id = request.pathVariable("userId");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findById(id), User.class);
    }


}
