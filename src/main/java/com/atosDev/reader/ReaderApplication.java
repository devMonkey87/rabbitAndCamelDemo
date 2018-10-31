package com.atosDev.reader;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReaderApplication {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// SpringApplication.run(ReaderApplication.class, args);

		Thread.sleep(30000);
	}
}
