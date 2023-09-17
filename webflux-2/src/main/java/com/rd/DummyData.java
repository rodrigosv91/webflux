package com.rd;

import com.rd.document.Playlist;
import com.rd.repository.PlaylistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

//@Component
//public class DummyData implements CommandLineRunner {
//    private final PlaylistRepository playlistRepository;
//
//    DummyData(PlaylistRepository playlistRepository){
//        this.playlistRepository = playlistRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        playlistRepository.deleteAll()
//                .thenMany(
//                        Flux.just("Dummy Data 1","Dummy Data 2","Dummy Data 3","Dummy Data 4", "Dummy Data 5")
//                                .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
//                                .flatMap(playlistRepository::save))
//                .subscribe(System.out::println);
//    }
//}
