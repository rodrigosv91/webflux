package com.webflux3.configuration;

import com.webflux3.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> routes(UserHandler handler){
        return RouterFunctions
                .route(GET("/handler/user").and(accept(MediaType.APPLICATION_JSON)), handler::getAllUsers)
                .andRoute(GET("/handler/user/{userId}").and(contentType(MediaType.APPLICATION_JSON)), handler::getUserById)
                .andRoute(POST("/handler/user").and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(PUT("/handler/user/{userId}").and(contentType(MediaType.APPLICATION_JSON)), handler::updateUserById)
                .andRoute(DELETE("/handler/user/{userId}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteUserById);
    }

}
