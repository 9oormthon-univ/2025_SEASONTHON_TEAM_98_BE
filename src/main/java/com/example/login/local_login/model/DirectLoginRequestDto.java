package com.example.login.local_login.model;

import lombok.Data;

@Data
public class DirectLoginRequestDto {
    private String email;
    private String password;
}