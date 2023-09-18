package com.rd.services;

import com.rd.document.Playlist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {

    Flux<Playlist> findaAll();
    Mono<Playlist> findById(String id);
    Mono<Playlist> save(Playlist playlist);

}
