package ru.com.m74.example.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.com.m74.example.webflux.dto.Example;

@RestController
public class DefaultController {

    @GetMapping("/")
    public Mono<String> index() {
        return Mono.just("Hello world");
    }

    @GetMapping("/flux")
    public Flux<Example> flux() {
        return Flux.just("one", "two").map(Example::new);
    }
}
