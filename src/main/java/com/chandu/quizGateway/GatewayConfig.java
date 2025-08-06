package com.chandu.quizGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("question-service", r -> r
                        .path("/question/**")
                        .filters(f -> f.rewritePath("/question/(?<segment>.*)", "/${segment}"))
                        .uri("lb://Question-Service"))
                .route("quiz-service", r -> r
                        .path("/quiz/**")
                        .filters(f -> f.rewritePath("/quiz/(?<segment>.*)", "/${segment}"))
                        .uri("lb://quiz-service"))
                .build();
    }
} 