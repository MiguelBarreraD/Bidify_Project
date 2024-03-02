package edu.ieti.bidify;

import edu.ieti.bidify.model.Producto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BidifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidifyApplication.class, args);
	}
}
