package org.example.webflux4essentials.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.example.webflux4essentials.domain.Anime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@SecurityScheme(
        name = "Basic Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public interface AnimeApi {

    @Operation(
            summary = "List all animes",
            security = @SecurityRequirement(name = "Basic Authentication"),
            tags = {"anime"})
    Flux<Anime> listAll();

    @Operation(
            summary = "Find an anime by ID",
            security = @SecurityRequirement(name = "Basic Authentication"),
            tags = {"anime"})
    Mono<Anime> findById(int id);

    @Operation(
            summary = "Create a new anime",
            security = @SecurityRequirement(name = "Basic Authentication"),
            tags = {"anime"})
    Mono<Anime> save(Anime anime);

    @Operation(
            summary = "Create multiple animes",
            security = @SecurityRequirement(name = "Basic Authentication"),
            tags = {"anime"})
    Flux<Anime> saveBatch(List<Anime> animes);

    @Operation(
            summary = "Update an anime by ID",
            security = @SecurityRequirement(name = "Basic Authentication"),
            tags = {"anime"})
    Mono<Void> update(int id, Anime anime);

    @Operation(
            summary = "Delete an anime by ID",
            security = @SecurityRequirement(name = "Basic Authentication"),
            tags = {"anime"})
    Mono<Void> delete(int id);
}
