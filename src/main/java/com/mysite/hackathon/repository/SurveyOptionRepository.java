package com.mysite.hackathon.repository;

import com.mysite.hackathon.entity.SurveyOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyOptionRepository extends JpaRepository<SurveyOption, Long> {
}

