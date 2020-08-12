package com.juliuskrah.server;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class Application implements WebFilter {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private String[] list = { "Deborah", "Freda", "Fakyiwa", "Loretta", "Bevelyn" };
	private Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routing() {
		return route().GET("/", accept(APPLICATION_JSON), this::handle).build();
	}

	Mono<ServerResponse> handle(ServerRequest request) {
		var example = new Example();
		example.setName(list[random.nextInt(list.length)]);
		log.info("Sending - {}", example.getName());
		return ServerResponse.ok().body(fromValue(example));
	}

	private static class Example {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		var hostname = exchange.getRequest().getURI().getHost();
		log.info("Hostname - {}", hostname);
		return chain.filter(exchange);
	}

}
