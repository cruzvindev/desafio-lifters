package com.lifters.eleicoesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EleicoesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EleicoesApiApplication.class, args);
	}

}
