package com.arcesi.rabbitMQ.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQConsomer {

	
	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void consume(final String message) {
		log.info(String.format("Message recieved ->%s", message));
	}
}
