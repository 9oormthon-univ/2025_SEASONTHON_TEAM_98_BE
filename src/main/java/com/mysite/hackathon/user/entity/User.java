package com.mysite.hackathon.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 로그인/인증 정보
    @Column(nullable = false, unique = true)
    private String email;       // 로컬 로그인 / 소셜 공통 식별자
    private String password;    // 로컬 로그인용 (소셜로그인 사용자는 null 가능)
    private String oauth2Id;    // 소셜 로그인 ID
    private String provider;    // google, kakao 등 제공자

    // 사용자 프로필 정보
    private String name;        // 사용자 이름
    private String username;    // 별칭/닉네임 (네가 쓰던 필드 유지)

    // 위치 기반 정보
    private Double locationLat;
    private Double locationLng;
    private String dong;
}
