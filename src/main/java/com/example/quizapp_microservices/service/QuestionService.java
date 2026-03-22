package com.example.quizapp_microservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.quizapp_microservices.entity.Question;
import com.example.quizapp_microservices.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public List<Question> getQuestionsByCategory(String category){
		return questionRepository.findByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionRepository.save(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public String deleteQuestionById(int Id) {
		// TODO Auto-generated method stub
		 questionRepository.deleteById(Id);
		 return "Deleted";
	}
}
