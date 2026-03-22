package com.example.quizapp_microservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp_microservices.entity.Question;
import com.example.quizapp_microservices.entity.QuestionWrapper;
import com.example.quizapp_microservices.entity.Quiz;
import com.example.quizapp_microservices.entity.Response;
import com.example.quizapp_microservices.repository.QuestionRepository;
import com.example.quizapp_microservices.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		// TODO Auto-generated method stub
		List<Question> question=questionRepository.findRandomQuestionsByCategory(category,numQ);
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(question);
		quizRepository.save(quiz);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz=quizRepository.findById(id);
		List<Question> questionFromDB=quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser=new ArrayList<>();
		for(Question q:questionFromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),
					q.getQuestionTitle(),q.getOption1(),q.getOption2(),
					q.getOption3(),q.getOption4());
			questionForUser.add(qw);
		}
		
		
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		// TODO Auto-generated method stub
		Quiz quiz=quizRepository.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
