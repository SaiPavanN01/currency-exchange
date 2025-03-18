package com.pavan.microservices.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
//	@Retry(name="sample-api",fallbackMethod="hardcodedResponse")
//	@CircuitBreaker(name ="sample-api",fallbackMethod="hardcodedResponse")
//	@RateLimiter(name ="defualt")
	@Bulkhead(name="defualt")
	public String sampleApi() {
		logger.info("Sample api call received");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8089/sum-dummy-response", String.class);
//		return "Sample API";
		return entity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
