package com.mysite.hackathon.local_login.repository;

import com.mysite.hackathon.local_login.model.User; // ✅ 올바르게 수정
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LocalUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByOauth2IdAndProvider(String oauth2Id, String provider);
}
