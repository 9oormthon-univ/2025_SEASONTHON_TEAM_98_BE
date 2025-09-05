package com.mysite.hackathon.service;

import com.mysite.hackathon.entity.SurveyAnswer;
import com.mysite.hackathon.entity.SurveyQuestion;
import com.mysite.hackathon.repository.SurveyAnswerRepository;
import com.mysite.hackathon.repository.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyAnswerService {

    private final SurveyAnswerRepository answerRepository;
    private final SurveyQuestionRepository questionRepository;

    public SurveyAnswer saveAnswer(Long userId, Long questionId, String selectedOption) {
        SurveyQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        SurveyAnswer answer = SurveyAnswer.builder()
                .userId(userId)
                .question(question)
                .selectedOption(selectedOption)
                .build();

        return answerRepository.save(answer);
    }

    public List<SurveyAnswer> getAnswersByUser(Long userId) {
        return answerRepository.findByUserId(userId);
    }
}
