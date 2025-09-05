package com.mysite.hackathon.survey_question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SurveyQuestionDTO {
    private Long id;
    private String questionText;
    private List<String> options;
}
