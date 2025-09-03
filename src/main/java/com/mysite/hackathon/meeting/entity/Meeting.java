package com.mysite.hackathon.meeting.entity;

import com.mysite.hackathon.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meeting")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Meeting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private User host;

    private Double locationLat;
    private Double locationLng;
    private String address;

    private String dong;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String restriction;
    private String genderLimit;
    private Boolean hasAfterparty;
}
