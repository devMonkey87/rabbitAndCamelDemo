package com.atosDev.reader.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atosDev.reader.entity.Queue;
import com.atosDev.reader.rabbitMQ.RabbitMqConfig;

@Component
public class Deleteme {
	public static int contadorAcceso = 0;
	@Autowired
	protected QueueService queueService;
//necesario para trabajar con cliente Rabbit
	@Autowired
	protected RabbitTemplate rabbitTemplate;

	@Autowired
	protected RabbitMqConfig rabbitconf;

	@PostConstruct
	public void init() {
		System.out.println("**************************************************************************************");
	}

	public void execute() {
		List<Queue> list = queueService.getAllData();
		// mandaAlaCola(list);
		// borraenBd(list)
	}

	// lo llamamos desde el camelcontext.xml para que se ejecute con un temporizador
	// manda a la cola de rabbit los registros en la BBDD cada x segundos
	public void mandaALaCola() {

		List<Queue> list = queueService.getAllData();

		if (!list.isEmpty()) {

			for (Queue queue : list) {
				// convierte a string los rows de la BBDD y los manda a rabbit
				rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY,
						queue.toString());

				borraenBd(queue.getId());

			}
//cuenta las veces que se accede a este método, para testear
			contadorAcceso++;
			System.out.println("VECES ENTRADO A MÉTODO MANDARCOLA= " + contadorAcceso);
			System.out.println("VECES");
			System.out.println("VECES");
			System.out.println("VECES");
			System.out.println("VECES");
			System.out.println("VECES");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();

		} else {
			// si no se obtienen mas registros de la BBDD significa que se han enviado todos
			// a rabbit y detenemos programa
			System.out.println("FIN!");
			rabbitconf.admin().purgeQueue(RabbitMqConfig.QUEUE_NAME, false);
		}

	}

	public void borraenBd(int id) {
		queueService.deleteQueue(id);

	}
}