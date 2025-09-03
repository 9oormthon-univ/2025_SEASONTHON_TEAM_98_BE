package com.mysite.hackathon.meeting.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MeetingRequestDto {
    private String title;
    private String description;
    private String category;
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
