package com.example.oxquiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="quiz")
@Data
public class QuizEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="quiz",length = 200)
    private String quiz;
    private String answer;
    @Column(name="name",length = 10)
    private String name;
}
