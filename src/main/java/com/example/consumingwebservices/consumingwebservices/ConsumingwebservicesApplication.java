package com.example.consumingwebservices.consumingwebservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ws.calculator.*;

@SpringBootApplication
@RestController
public class ConsumingwebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingwebservicesApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(calculatorClient client) {
		return args -> {

			AddResponse Response = client.getAddResult(5, 8);

			MultiplyResponse Response2 = client.getMultiplyResult(5, 5);

			DivideResponse Response3 = client.getDivideResult(15, 5);

			SubtractResponse Response4 = client.getSubtractResult(15, 5);

			int information = Response.getAddResult();

			int information2 = Response2.getMultiplyResult();

			int information3 = Response3.getDivideResult();

			int information4 = Response4.getSubtractResult();

			System.err.println("*** Output ****** ");
			System.err.println("  resultat add : " + information + " \n  resultat Multiply : " + information2
					+ " \n  resultat Divide  : " + information3 + " \n  resultat Subtract : " + information4);

		};
	}

	@GetMapping("/getData/{country}")
	@ResponseBody
	private Object getdata(@PathVariable("country") String country) {
		RestTemplate rest = new RestTemplate();
		Object Result = rest.getForObject("http://universities.hipolabs.com/search?country=" + country,
				Object.class);
		return Result;

	}
}
