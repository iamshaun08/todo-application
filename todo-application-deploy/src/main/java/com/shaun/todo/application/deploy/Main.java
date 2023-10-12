package com.shaun.todo.application.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.shaun.*"})
@EnableJpaRepositories(basePackages = {"com.shaun"})
@EntityScan(basePackages = {"com.shaun"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}