package com.cadastro.relacional.empresa.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cadastro de Empresas")
                        .description("Crud de empresa em banco de dados relacional.")
                        .contact(new Contact().name("Leila Fernanda da Silva").email("leilafernandadasilva@gmail.com"))
                        .version("1.0.0"));
    }
}
