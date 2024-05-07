package com.yancorrea.publisherapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PublisherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherApiApplication.class, args);
		System.out.println("Working is working rofl");
	}

}
