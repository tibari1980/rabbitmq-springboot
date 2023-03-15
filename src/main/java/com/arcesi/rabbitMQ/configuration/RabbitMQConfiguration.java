package com.arcesi.rabbitMQ.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class contient la configuration du RabbitMQ: queue, exchange
 * 
 * @author tibari
 *
 */
@Configuration
public class RabbitMQConfiguration {

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing.key}")
	private String routingName;

	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueueName;

	@Value("${rabbitmq.routing.key.json}")
	private String jsonRoutingKey;

	// spring bean for rabbitmq: Queue
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	// spring bean for rabbitMQ :Queue Json
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueueName);
	}

	// spring bean for RabbitMq : Exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	// spring bean :binding betweenn queue and exchange usign routingKey
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingName);
	}

	// spring bean : binding between jsonQueue et exchange using jsonRoutingKey
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingKey);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template=new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter());
		return template;
	}
}
