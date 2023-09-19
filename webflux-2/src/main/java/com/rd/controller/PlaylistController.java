package com.rd.controller;

import com.rd.document.Playlist;
import com.rd.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//old rest handler
//@RestController
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping(value = "/playlist" )
    public Flux<Playlist> getPlaylist(){
        return playlistService.findaAll();
    }

    @GetMapping(value = "/playlist/{id}")
    public Mono<Playlist> getPlaylistById(@PathVariable String id){
        return playlistService.findById(id);
    }

    @PostMapping(value = "/playlist")
    public Mono<Playlist> save(@RequestBody Playlist playlist){
        return playlistService.save(playlist);
    }
}
