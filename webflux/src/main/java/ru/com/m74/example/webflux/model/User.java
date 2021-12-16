package ru.com.m74.example.webflux.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    private String name;

    private String password;
}
