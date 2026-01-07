package com.PrasanthProjects.Pebble;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.PrasanthProjects.Pebble")
public class PebbleApplication {
	public static void main(String[] args) {
		SpringApplication.run(PebbleApplication.class, args);
	}
}
