package com.mysite.hackathon.survey_result.repository;

import com.mysite.hackathon.survey_result.entity.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    SurveyResult findByUserId(Long userId);
}
