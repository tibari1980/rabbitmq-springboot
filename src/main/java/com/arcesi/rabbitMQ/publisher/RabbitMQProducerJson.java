package com.arcesi.rabbitMQ.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arcesi.rabbitMQ.dto.Client;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQProducerJson {

	
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	
	@Value("${rabbitmq.routing.key.json}")
	private String routingKeyJson;
	
	
	private RabbitTemplate rabbitTemplate;
	
	public RabbitMQProducerJson(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	public void sendJsonObject(Client client) {
		
		log.info(String.format("Json message sent  -> %s", client.toString()));
		rabbitTemplate.convertAndSend(exchangeName, routingKeyJson, client);
	}
}
