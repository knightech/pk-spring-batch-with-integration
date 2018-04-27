package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@Configuration
@ImportResource("classpath*:/integration-context.xml")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
