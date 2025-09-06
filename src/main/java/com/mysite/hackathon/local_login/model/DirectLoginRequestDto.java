package com.mysite.hackathon.local_login.model;

import lombok.Data;

@Data
public class DirectLoginRequestDto {
    private String email;
    private String password;
}