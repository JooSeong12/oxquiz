package com.example.oxquiz.vo;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.entity.QuizEntity;
import com.example.oxquiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@Service
public class MethodZip {
    Random random = new Random();
    private final QuizRepository quizRepository;

    public MethodZip(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public List<QuizDto> showList(){
        List<QuizEntity> quizList = quizRepository.findAll();
        List<QuizDto> quizDtoList = quizRepository.findAll().stream()
                .map(x-> QuizDto.fromQuizEntity(x)).toList();
        return quizDtoList;
    }

    public QuizDto play(){
        return QuizDto.fromQuizEntity(quizRepository.random());
    }

}
