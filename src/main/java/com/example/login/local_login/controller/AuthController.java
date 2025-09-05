package com.example.login.local_login.controller;

import com.example.login.local_login.model.User;
import com.example.login.local_login.repository.LocalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final LocalUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup"; // signup.html 파일을 반환
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login"; // 회원가입 성공 후 로그인 페이지로 리다이렉션
    }
}
