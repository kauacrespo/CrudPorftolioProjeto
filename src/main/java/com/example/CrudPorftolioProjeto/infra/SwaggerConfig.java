package com.example.CrudPorftolioProjeto.infra;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("API De Biblioteca - Crespo")
                .version("1.0")
                .description("Sistema de Gerenciamento de autores e livros com Soft Delete, DTOS e validações.")
                .contact(new Contact()
                        .name("Kauã Carlos Silva Crespo")
                        .email("kauacarlosbr3@gmail.com")
                        .url("https://github.com/kauacrespo")));
    }
}
