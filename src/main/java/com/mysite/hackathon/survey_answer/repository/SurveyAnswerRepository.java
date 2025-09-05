package com.mysite.hackathon.survey_answer.repository;

import com.mysite.hackathon.survey_answer.entity.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {

    List<SurveyAnswer> findByUserId(Long userId);

}
