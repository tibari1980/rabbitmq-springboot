package com.arcesi.rabbitMQ.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author tibari
 * Ingénieur developpement
 *
 */
/**
 * 
 * Producer
 *
 */
@Service
@Slf4j
public class RabbitMQProducer {

	private RabbitTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;

	}

	public void sendMessage(final String message) {
		log.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);

	}

}
