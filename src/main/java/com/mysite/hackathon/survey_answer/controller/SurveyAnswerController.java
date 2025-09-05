package com.mysite.hackathon.survey_answer.controller;

import com.mysite.hackathon.survey_answer.service.SurveyAnswerService;
import com.mysite.hackathon.survey_answer.entity.SurveyAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/survey/answers")
@RequiredArgsConstructor
public class SurveyAnswerController {

    private final SurveyAnswerService answerService;

    @PostMapping
    public SurveyAnswer saveAnswer(@RequestBody Map<String, String> request) {
        Long userId = Long.valueOf(request.get("userId"));
        Long questionId = Long.valueOf(request.get("questionId"));
        String selectedOption = request.get("selectedOption");

        return answerService.saveAnswer(userId, questionId, selectedOption);
    }

    @GetMapping
    public List<SurveyAnswer> getAnswers(@RequestParam Long userId) {
        return answerService.getAnswersByUser(userId);
    }
}
