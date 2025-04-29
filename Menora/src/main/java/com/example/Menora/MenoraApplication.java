package com.example.Menora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MenoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenoraApplication.class, args);
	}

}
