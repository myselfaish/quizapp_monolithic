package com.example.quizapp_microservices.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
public class Response {
	private int id;
	private String response;
}
