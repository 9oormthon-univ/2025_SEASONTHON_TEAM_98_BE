package com.example.login.model;

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

    // 소셜 로그인 시 사용할 oauth2Id (직접 로그인 시에는 null)
    @Column(unique = true)
    private String oauth2Id;

    // 소셜 로그인 시 사용할 provider (직접 로그인 시에는 null)
    private String provider;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;
}