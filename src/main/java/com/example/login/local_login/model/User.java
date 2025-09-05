package com.example.login.local_login.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique = true)
    private String oauth2Id;

    private String provider;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;
}