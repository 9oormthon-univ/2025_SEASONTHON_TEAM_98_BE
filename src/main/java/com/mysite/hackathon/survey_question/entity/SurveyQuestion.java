package com.mysite.hackathon.survey_question.entity;

import com.mysite.hackathon.survey_option.entity.SurveyOption;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<SurveyOption> options;
}
