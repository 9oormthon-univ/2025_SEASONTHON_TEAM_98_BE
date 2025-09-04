package com.mysite.hackathon.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantInfoDto {
    private Long userId;
    private String username;
}
