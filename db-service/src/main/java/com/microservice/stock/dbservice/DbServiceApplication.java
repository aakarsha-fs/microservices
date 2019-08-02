package com.microservice.stock.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.microservice.stock.dbservice.*")
@SpringBootApplication
public class DbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}

}
