package com.example.quizapp_microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizapp_microservices.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {

}
