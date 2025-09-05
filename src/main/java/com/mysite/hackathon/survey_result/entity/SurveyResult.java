package com.mysite.hackathon.survey_result.entity;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "survey_results")
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String type; // 감정 캐릭터 타입
    private String description; // 설명
}
