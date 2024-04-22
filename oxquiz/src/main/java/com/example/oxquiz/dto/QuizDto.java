package com.example.oxquiz.dto;

import com.example.oxquiz.entity.QuizEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class QuizDto {
    private long id;
    private String quiz;
    private String answer;
    private String name;

    public static QuizDto fromQuizEntity(QuizEntity quizEntity){
        return new QuizDto(quizEntity.getId(), quizEntity.getQuiz(), quizEntity.getAnswer(), quizEntity.getName());
    }
    public QuizEntity fromQuizDto(QuizDto dto){
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setId(dto.getId());
        quizEntity.setQuiz(dto.getQuiz());
        quizEntity.setAnswer(dto.getAnswer());
        quizEntity.setName(dto.getName());
        return quizEntity;
    }
}
