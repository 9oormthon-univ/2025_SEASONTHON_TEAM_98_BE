package com.mysite.hackathon.survey_result.controller;

import com.mysite.hackathon.survey_result.entity.SurveyResult;
import com.mysite.hackathon.survey_result.service.SurveyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey/results")
@RequiredArgsConstructor
public class SurveyResultController {

    private final SurveyResultService resultService;

    @PostMapping("/calculate")
    public SurveyResult calculateResult(@RequestParam Long userId) {
        return resultService.calculateResult(userId);
    }

    @GetMapping
    public SurveyResult getResult(@RequestParam Long userId) {
        return resultService.getResult(userId);
    }
}
