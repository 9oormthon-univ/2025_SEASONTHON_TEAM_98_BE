package com.mysite.hackathon.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipationStatusDto {
    private Long meetingId;
    private Long userId;
    private String status; // "참여중" or "미참여"
}
