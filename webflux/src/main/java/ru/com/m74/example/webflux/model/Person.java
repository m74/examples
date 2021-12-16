package ru.com.m74.example.webflux.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("persons")
public class Person {
    @Id
    private Long id;

    @Column("full_name")
    private String name;

    private Sex sex;
}
