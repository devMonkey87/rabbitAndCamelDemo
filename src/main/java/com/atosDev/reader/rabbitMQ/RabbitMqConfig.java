package com.atosDev.reader.rabbitMQ;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	public static final String EXCHANGE_NAME = "exchangeLandeteNEW_VERSION";
	public static final String ROUTING_KEY = "routing_key_landeteNEW_VERSION";
	public static final String QUEUE_NAME = "ColaNEW_VERSION";
	private static final boolean IS_DURABLE_QUEUE = false;

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, IS_DURABLE_QUEUE);
	}
	// Crea un exchange de tipo topic y le asigna un nombre.

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	// Enlaza una cola con un exchange de tipo topic. Con with se define la clave
	// del enlace.
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	// Contenedor en el que se indican quiénes son los consumidores de las colas.

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	Receiver receiver() {
		return new Receiver();
	}

//Adaptador para indicar quién recibe el mensaje y qué método lo procesa.
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, Receiver.RECEIVE_METHOD_NAME);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory();
	}

	@Bean
	public AmqpAdmin admin() {
		return new RabbitAdmin(connectionFactory());
	}
}
