package ru.com.m74.example.webflux.service;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.com.m74.example.webflux.model.Person;
import ru.com.m74.example.webflux.repository.PersonRepository;

import javax.annotation.Resource;

@Service
public class PersonService {

    @Resource
    private PersonRepository repository;

    @Resource
    private R2dbcEntityTemplate template;

    @Resource
    private DatabaseClient client;


    public Flux<Person> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Mono<Person> save(Person person) {
        return repository.save(person);
    }

    public void test() {
//        client.sql("select * from persons").
    }
}
