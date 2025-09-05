package com.mysite.hackathon.survey_answer.entity;

import com.mysite.hackathon.survey_question.entity.SurveyQuestion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "survey_answer")
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private SurveyQuestion question;

    private String selectedOption;
}
