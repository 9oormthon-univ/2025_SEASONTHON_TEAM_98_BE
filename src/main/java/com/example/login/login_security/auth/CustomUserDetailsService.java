package com.example.login.login_security.auth;

import com.example.login.local_login.model.User;
import com.example.login.local_login.repository.LocalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LocalUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일로 데이터베이스에서 사용자 정보(User)를 찾아옵니다.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // User 엔티티의 정보를 사용하여 UserDetails 객체를 생성하여 반환합니다.
        // Spring Security가 이 UserDetails 객체의 비밀번호와 사용자가 입력한 비밀번호를 비교합니다.
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
