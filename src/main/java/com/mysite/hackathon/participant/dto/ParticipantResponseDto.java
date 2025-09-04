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
    }//모임에 참여/취소 결과 응답할 때 사용. join API가 성공하면 참가자 ID, 유저 ID, 모임 ID, 참여 시간을 응답
}
