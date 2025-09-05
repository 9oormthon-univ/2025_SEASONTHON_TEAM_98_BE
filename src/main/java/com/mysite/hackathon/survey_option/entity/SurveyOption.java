package com.mysite.hackathon.survey_option.entity;

import com.mysite.hackathon.survey_question.entity.SurveyQuestion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private SurveyQuestion question;
}
