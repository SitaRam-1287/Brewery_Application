package com.brewery.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
<<<<<<< HEAD
=======

>>>>>>> 62de3ab155bd75d771c94a565561bd7c6bb138aa
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
