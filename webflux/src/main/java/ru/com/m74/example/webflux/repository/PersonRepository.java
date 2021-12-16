package ru.com.m74.example.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.com.m74.example.webflux.model.Person;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
    Flux<Person> findAllByNameLike(String fullName);
}
