package com.mysite.hackathon.participant.entity;

import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.local_login.model.User; // ✅ 수정
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_participants")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MeetingParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;   // ✅ local_login.model.User 사용

    private LocalDateTime joinedAt;
}
