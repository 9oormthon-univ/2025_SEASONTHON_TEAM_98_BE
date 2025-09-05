package com.example.login.local_login.repository;

import com.example.login.local_login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LocalUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauth2IdAndProvider(String oauth2Id, String provider);
    Optional<User> findByEmail(String email);
}