package com.arcesi.rabbitMQ.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.rabbitMQ.publisher.RabbitMQProducer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Data
@Slf4j
@RequestMapping(value = "/api/v1")
public class MessageRestController {

	private RabbitMQProducer producer;

	//http://localhost:8080/api/v1/publish?message=Bonjour les geeks RabbiMQ
	@GetMapping(value = "/publish")
	public ResponseEntity<String> sentMessage(@RequestParam(name = "message") String message) {
		log.info("Inside Méthod sentMessage  in MessageRestController {} ", message);
		for(int i=0;i<20;i++) {
			producer.sendMessage(message+i);	
		}
		
		
		return ResponseEntity.ok("Message  sent to RabbitMQ ......");
	}

	public MessageRestController(RabbitMQProducer producer) {
		this.producer = producer;
	}
}
