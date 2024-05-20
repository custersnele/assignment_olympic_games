package be.pxl.olympicgames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlympicGames {

	public static void main(String[] args) {
		SpringApplication.run(OlympicGames.class, args);
	}

	// Don't forget to add the necessary annotations:
	// @ServletComponentScan -> to use servlets
	// @EnableAsync -> to use @Async
	// @EnableScheduling -> to use @Scheduled

}
