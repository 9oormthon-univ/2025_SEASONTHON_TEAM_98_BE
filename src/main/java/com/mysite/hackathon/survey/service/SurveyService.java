package com.mysite.hackathon.survey.service;

import com.mysite.hackathon.survey_question.dto.SurveyQuestionDTO;
import com.mysite.hackathon.survey_question.entity.SurveyQuestion;
import com.mysite.hackathon.survey_question.repository.SurveyQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyQuestionRepository questionRepository;

    public List<SurveyQuestionDTO> getAllQuestionsWithOptions() {
        List<SurveyQuestion> questions = questionRepository.findAll();

        return questions.stream()
                .map(q -> new SurveyQuestionDTO(
                        q.getId(),
                        q.getQuestionText(),
                        q.getOptions().stream()
                                .map(option -> option.getOptionText())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
