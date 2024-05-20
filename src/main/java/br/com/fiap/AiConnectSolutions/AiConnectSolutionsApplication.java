package br.com.fiap.AiConnectSolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "AI Connect Solutions",
		version = "1.0",
		summary = "API do projeto AIConnectSolutions - Sistema de Relacionamento de Clientes"
	)
)
public class AiConnectSolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiConnectSolutionsApplication.class, args);
	}

}