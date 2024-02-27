package edu.eci.arsw.bidify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.bidify"})
public class BidifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidifyApplication.class, args);
	}
}
