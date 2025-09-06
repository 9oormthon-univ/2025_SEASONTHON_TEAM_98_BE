package com.mysite.hackathon.local_login.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder   // ✅ 추가 (User.builder() 사용 가능)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 로컬 로그인 비밀번호 (소셜 로그인 사용자는 null 허용)
    @Column(nullable = true)
    private String password;

    // 소셜 로그인 식별자 (Google sub, Kakao id 등)
    @Column(unique = true)
    private String oauth2Id;

    // 소셜 로그인 제공자 (google, kakao 등)
    private String provider;

    // 사용자 이름 (로컬/소셜 공통)
    private String name;

    // 이메일 (로컬/소셜 공통, 반드시 유니크)
    @Column(nullable = false, unique = true)
    private String email;
}
