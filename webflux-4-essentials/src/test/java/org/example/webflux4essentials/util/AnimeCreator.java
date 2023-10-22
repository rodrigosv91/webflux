package org.example.webflux4essentials.util;

import org.example.webflux4essentials.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Tensei Shitara Slime Datta Ken")
                .build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
                .id(1)
                .name("Tensei Shitara Slime Datta Ken")
                .build();
    }

    public static Anime createdValidUpdatedAnime() {
        return Anime.builder()
                .id(1)
                .name("Tensei Shitara Slime Datta Ken 2")
                .build();
    }
}