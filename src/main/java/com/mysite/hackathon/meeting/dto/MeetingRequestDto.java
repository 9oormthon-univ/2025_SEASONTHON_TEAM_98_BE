package com.mysite.hackathon.meeting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MeetingRequestDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotBlank(message = "설명은 필수 입력 값입니다.")
    @Size(max = 500, message = "설명은 최대 500자까지 입력 가능합니다.")
    private String description;

    @NotBlank(message = "카테고리는 필수 입력 값입니다.")
    private String category;

    @NotNull(message = "위도는 필수 입력 값입니다.")
    private Double locationLat;

    @NotNull(message = "경도는 필수 입력 값입니다.")
    private Double locationLng;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    @NotBlank(message = "동 이름은 필수 입력 값입니다.")
    private String dong;

    @NotNull(message = "시작 시간은 필수 입력 값입니다.")
    private LocalDateTime startTime;

    @NotNull(message = "종료 시간은 필수 입력 값입니다.")
    private LocalDateTime endTime;

    private String restriction;   // 선택값
    private String genderLimit;   // 선택값
    private Boolean hasAfterparty; // 선택값

    private Integer maxParticipants; // ✅ 추가 (최대 인원 제한)
}
