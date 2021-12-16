package ru.com.m74.example.webflux.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.com.m74.example.webflux.model.Person;
import ru.com.m74.example.webflux.service.PersonService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService service;

    @GetMapping
    public Flux<Person> all() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Person> create(@RequestBody Person person) {
        return service.save(person);
    }

    @PostMapping("/{id}")
    public Mono<Person> save(@PathVariable Long id, @RequestBody Person person) {
        person.setId(id);
        return service.save(person);
    }
}
