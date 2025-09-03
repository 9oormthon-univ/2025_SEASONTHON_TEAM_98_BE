package com.mysite.hackathon.user.repository;

import com.mysite.hackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
