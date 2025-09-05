package com.mysite.hackathon.user.dto;

import lombok.Data;

@Data
public class DirectLoginRequestDto {
    private String email;
    private String password;
}