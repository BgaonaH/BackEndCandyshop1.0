package com.candyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title="Ecommerce Candyshop Sena proyect",
		description="Welcome to the documentation for the eCommerce Project API! This API provides a set of endpoints and functionalities to interact with our eCommerce platform programmatically. With this API, you can build applications, integrations, and automate various tasks related to our online store.",
		version="v1",
		contact=@Contact(
				name="Eduardo Gaona, Miguel Calderon ",
				email="psy.egaona@gmail.com, miguelitoc455@gmail.com"
				),
		license=@License(
				name="Gaona-Calderon"
				)
		))
public class EcommerceCandyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceCandyshopApplication.class, args);
	}

}
