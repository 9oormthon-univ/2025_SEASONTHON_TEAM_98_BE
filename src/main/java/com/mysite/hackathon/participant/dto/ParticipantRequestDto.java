package com.mysite.hackathon.participant.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ParticipantRequestDto {
    private Long userId;
}//모임에 참여/취소 요철할때 사용
