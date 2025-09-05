package com.mysite.hackathon.repository;

import com.mysite.hackathon.entity.SurveyAnswer;
import com.mysite.hackathon.entity.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {

    List<SurveyAnswer> findByUserId(Long userId);

}
