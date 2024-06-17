package com.marija.insurance;

import com.marija.insurance.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class InsuranceApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CircuitBreakerConfig circuitBreakerConfig() {
		return CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofSeconds(10))
				.slidingWindowSize(10)
				.build();
	}
	@Bean
	public RetryConfig retryConfig() {
		return RetryConfig.custom()
				.maxAttempts(3)
				.waitDuration(Duration.ofMillis(500))
				.build();
	}

	@Bean
	public TimeLimiterConfig timeLimiterConfig() {
		return TimeLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(5))
				.build();
	}
	public static void main(String[] args) {

		SpringApplication.run(InsuranceApplication.class, args);
	}

}
