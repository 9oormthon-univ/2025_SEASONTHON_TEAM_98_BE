package com.mysite.hackathon.survey_option.repository;

import com.mysite.hackathon.survey_option.entity.SurveyOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyOptionRepository extends JpaRepository<SurveyOption, Long> {
}

