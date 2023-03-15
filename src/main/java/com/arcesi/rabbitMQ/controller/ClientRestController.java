package com.arcesi.rabbitMQ.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.rabbitMQ.dto.Client;
import com.arcesi.rabbitMQ.publisher.RabbitMQProducerJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Data
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/v1")
public class ClientRestController {

	
	private RabbitMQProducerJson rabbitMQProducerJson;
	
	@PostMapping(value="/publish")
	public ResponseEntity<Client> sendClient(@RequestBody Client client){
		log.info("Inside méthoe SendClient in ClientRestController : {}");
		rabbitMQProducerJson.sendJsonObject(client);
		return new ResponseEntity<Client>(client,HttpStatus.CREATED);
	}
	
	@GetMapping(value="/findClient")
	public ResponseEntity<Client> getClient(){
		Client client=new Client();
		client.setCode(new Long(1200));
		client.setAdresse("11 place des alpes 78280 Guyancourt");
		client.setFirstName("Zeroual");
		client.setLastName("Tibari");
		client.setActived(Boolean.TRUE);
		
		return new ResponseEntity<Client>(client,HttpStatus.ACCEPTED);
	}
}
