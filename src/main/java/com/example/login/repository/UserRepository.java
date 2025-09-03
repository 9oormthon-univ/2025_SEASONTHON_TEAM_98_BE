package com.example.login.repository;

import com.example.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauth2IdAndProvider(String oauth2Id, String provider);
}