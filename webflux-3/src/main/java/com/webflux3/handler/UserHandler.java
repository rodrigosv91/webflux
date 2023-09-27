package com.webflux3.handler;

import com.webflux3.model.User;
import com.webflux3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return userService
                .findById(request.pathVariable("userId"))
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        //.body(user, User.class)
                        .bodyValue(user)
                )
                .switchIfEmpty(ServerResponse.notFound().build());

//        String id = request.pathVariable("userId");
//        return ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(userService.findById(id), User.class);
    }

    public Mono<ServerResponse> create(ServerRequest request){ //recebe a request sendo processada inplicitamente
        Mono<User> userReceived = request.bodyToMono(User.class); // resgata usuario no corpo da requisição

        return userReceived
                .flatMap(user -> ServerResponse     // acessa usuario dentro da stream como um dado só
                        .status(HttpStatus.CREATED) // define status da respota como criado (201)
                        .contentType(MediaType.APPLICATION_JSON)  // define o tipo do dado sendo retornado como JSON
                        .body(userService.createUser(user), User.class));   // cria o usuario
                                                                            // e serializa o dado retornado da criação como sendo do tipo User.class
    }

    public Mono<ServerResponse> updateUserById(ServerRequest request){
        String id = request.pathVariable("userId");
        Mono<User> updatedUser  = request.bodyToMono(User.class);

        return updatedUser
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.updateUser(id, user), User.class)
                );
    }

    public Mono<ServerResponse> deleteUserById(ServerRequest request){
        return userService.deleteUser(request.pathVariable("userId"))
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        //.bodyValue(user)
                        .body(user, User.class)

                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
