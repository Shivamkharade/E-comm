package com.e_comm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ECommApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		
		 System.setProperty("KEY", dotenv.get("KEY"));
		 System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));
		 System.setProperty("MYSQL_USERNAME",dotenv.get("MYSQL_USERNAME"));
		
		SpringApplication.run(ECommApplication.class, args);
	}

}
