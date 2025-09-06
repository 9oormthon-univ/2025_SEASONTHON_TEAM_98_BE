package com.mysite.hackathon.security.auth;

import com.mysite.hackathon.local_login.model.User;                 // ✅ 수정
import com.mysite.hackathon.local_login.repository.LocalUserRepository; // ✅ 수정
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LocalUserRepository userRepository; // ✅ 수정

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // User 엔티티 → Spring Security UserDetails 변환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())    // 이메일을 username 으로 사용
                .password(user.getPassword()) // 비밀번호 (소셜 로그인 유저는 null일 수 있음)
                .roles("USER")                // 기본 ROLE
                .build();
    }
}
