package com.mysite.hackathon.security.auth;

import com.mysite.hackathon.user.entity.User;
import com.mysite.hackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository; // ✅ UserRepository 로 변경

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // User 엔티티 → Spring Security UserDetails 변환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())   // 이메일을 username 으로 사용
                .password(user.getPassword()) // 비밀번호
                .roles("USER")                // 기본 ROLE
                .build();
    }
}
