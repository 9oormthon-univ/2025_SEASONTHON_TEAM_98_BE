package com.mysite.hackathon.participant.dto;

import com.mysite.hackathon.participant.entity.MeetingParticipant;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ParticipantResponseDto {
    private Long participantId;
    private Long userId;
    private Long meetingId;
    private LocalDateTime joinedAt;

    public static ParticipantResponseDto fromEntity(MeetingParticipant participant) {
        return ParticipantResponseDto.builder()
                .participantId(participant.getId())
                .userId(participant.getUser().getId())
                .meetingId(participant.getMeeting().getId())
                .joinedAt(participant.getJoinedAt())
                .build();
    }
}
