package com.atosDev.reader.rabbitMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atosDev.reader.service.Deleteme;

@Component
public class Receiver {

	@Autowired
	Deleteme delete;
	public static final String RECEIVE_METHOD_NAME = "receiveMessage";

	public void receiveMessage(String message) {
		System.out.println("Consumida una entrada de la BBDD***** <" + message + ">");

		String trim = message.substring(message.indexOf("=") + 1, message.indexOf(","));

		// consumido por el receptor, borramos de la DBase . Haciendolo así se forman
		// colas en Rabbit
		// delete.borraenBd(Integer.parseInt(trim));
	}

}
