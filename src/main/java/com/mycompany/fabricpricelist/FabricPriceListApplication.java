package com.mycompany.fabricpricelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FabricPriceListApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabricPriceListApplication.class, args);
	}

}
