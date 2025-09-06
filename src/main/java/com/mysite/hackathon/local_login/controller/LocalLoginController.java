package com.mysite.hackathon.local_login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocalLoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}