package com.mysite.hackathon.user.repository;

import com.mysite.hackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauth2IdAndProvider(String oauth2Id, String provider);
    Optional<User> findByEmail(String email);
}
