package com.ninova.purchaseorderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ninova.purchaseorderapp.entity")
@EnableJpaRepositories(basePackages = "com.ninova.purchaseorderapp.repository")
public class PurchaseOrderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseOrderAppApplication.class, args);
	}

}
