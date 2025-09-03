package com.mysite.hackathon.meeting.dto;

import com.mysite.hackathon.meeting.entity.Meeting;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MeetingResponseDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String address;
    private Double locationLat;
    private Double locationLng;

    public static MeetingResponseDto fromEntity(Meeting meeting) {
        return MeetingResponseDto.builder()
                .id(meeting.getId())
                .title(meeting.getTitle())
                .description(meeting.getDescription())
                .category(meeting.getCategory())
                .address(meeting.getAddress())
                .locationLat(meeting.getLocationLat())
                .locationLng(meeting.getLocationLng())
                .build();
    }
}
