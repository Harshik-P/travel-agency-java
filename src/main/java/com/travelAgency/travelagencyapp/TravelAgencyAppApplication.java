package com.travelAgency.travelagencyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TravelAgencyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyAppApplication.class, args);
	}

}
