package com.pavan.microservices.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class CircuitBreakerController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
	@Bulkhead(name="default")
	public String sampleApi() {
		logger.info("Sample api call received");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8089/sum-dummy-response", String.class);
		// Web Client
		String response = webClientBuilder.build()
				.get()
				.uri("http://localhost:8089/sum-dummy-response")
				.retrieve()
				.bodyToMono(String.class)
				.block();
//		return "Sample API";
		return response;
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
