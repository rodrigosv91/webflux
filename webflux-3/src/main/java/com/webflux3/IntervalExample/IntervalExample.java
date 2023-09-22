package com.webflux3.IntervalExample;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class IntervalExample {
    public static void main(String[] args) {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(5));

        intervalFlux.subscribe(value -> {
            System.out.println("Received value: " + value);
        });

        // Espera um tempo para que o fluxo tenha tempo de emitir valores antes de encerrar o programa.
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

