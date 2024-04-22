package com.example.oxquiz.repository;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    @Query(value = "select * from quiz order by rand() limit 1", nativeQuery = true)
    QuizEntity random();
}
