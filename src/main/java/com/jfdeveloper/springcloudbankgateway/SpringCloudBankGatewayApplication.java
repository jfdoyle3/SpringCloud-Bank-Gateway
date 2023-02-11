package com.jfdeveloper.springcloudbankgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudBankGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudBankGatewayApplication.class, args);
	}

	// Simple Route
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/get")
//						.filters(f -> f.addRequestHeader("Hello", "World"))
//						.uri("http://httpbin.org:80"))
//				.build();
//	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()

				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.host("*.circuitbreaker.com")
						.filters(f -> f.circuitBreaker(config -> config.setName("mycmd")))
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/bank")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://localhost:8080"))
				.build();
	}

}
