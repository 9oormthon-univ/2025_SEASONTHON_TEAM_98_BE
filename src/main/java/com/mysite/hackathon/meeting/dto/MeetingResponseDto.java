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

    private Long hostId;       // ✅ 모임장 ID
    private String hostName;   // ✅ 모임장 이름

    private int currentParticipants; // ✅ 현재 인원
    private Integer maxParticipants; // ✅ 제한 인원

    public static MeetingResponseDto fromEntity(Meeting meeting, int currentParticipants) {
        return MeetingResponseDto.builder()
                .id(meeting.getId())
                .title(meeting.getTitle())
                .description(meeting.getDescription())
                .category(meeting.getCategory())
                .address(meeting.getAddress())
                .locationLat(meeting.getLocationLat())
                .locationLng(meeting.getLocationLng())
                .hostId(meeting.getHost() != null ? meeting.getHost().getId() : null)
                .hostName(meeting.getHost() != null ? meeting.getHost().getUsername() : null)
                .currentParticipants(currentParticipants)
                .maxParticipants(meeting.getMaxParticipants())
                .build();
    }
}
