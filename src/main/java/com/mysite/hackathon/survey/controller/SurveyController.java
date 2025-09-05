package com.mysite.hackathon.survey.controller;

import com.mysite.hackathon.survey.service.SurveyService;
import com.mysite.hackathon.survey_question.dto.SurveyQuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/questions")
    public List<SurveyQuestionDTO> getQuestions() {
        return surveyService.getAllQuestionsWithOptions();
    }
}
