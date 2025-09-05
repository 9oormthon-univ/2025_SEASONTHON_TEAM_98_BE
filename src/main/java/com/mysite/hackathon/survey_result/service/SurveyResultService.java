package com.mysite.hackathon.survey_result.service;

import com.mysite.hackathon.survey_answer.entity.SurveyAnswer;
import com.mysite.hackathon.survey_result.entity.SurveyResult;
import com.mysite.hackathon.survey_answer.repository.SurveyAnswerRepository;
import com.mysite.hackathon.survey_result.repository.SurveyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyResultService {

    private final SurveyAnswerRepository answerRepository;
    private final SurveyResultRepository resultRepository;

    public SurveyResult calculateResult(Long userId) {
        List<SurveyAnswer> answers = answerRepository.findByUserId(userId);

        if(answers.isEmpty()) {
            throw new IllegalArgumentException("No answers found for user");
        }

        String[] types = {
                "작은 성공이 필요한 수달 타입",
                "긍정 에너지가 가득한 햄스터 타입",
                "신중한 계획가 부엉이 타입",
                "모험을 즐기는 여우 타입",
                "감정 표현이 솔직한 고양이 타입",
                "팀워크를 중시하는 개 타입",
                "차분한 관찰자 판다 타입"
        };

        int index = (int)(answers.get(0).getQuestion().getId() % 7);
        String type = types[index];
        String description = "당신의 마음에 귀 기울여봤어요.";

        SurveyResult result = SurveyResult.builder()
                .userId(userId)
                .type(type)
                .description(description)
                .build();

        return resultRepository.save(result);
    }

    public SurveyResult getResult(Long userId) {
        return resultRepository.findByUserId(userId);
    }
}
