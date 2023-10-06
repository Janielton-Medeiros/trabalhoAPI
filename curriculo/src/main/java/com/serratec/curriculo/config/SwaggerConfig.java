package com.serratec.curriculo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
// Anotação indicando que esta classe é uma configuração para o Spring
@EnableWebMvc
public class SwaggerConfig {
	
    @Bean
    //Esse objeto será gerenciado pelo Spring e pode ser injetado em outras partes do seu aplicativo que precisam dele. É uma maneira de configurar e criar objetos de forma centralizada no contexto do Spring.
    public Docket api() {// Inicializa um novo Docket usando o tipo de documentação SWAGGER_2
        return new Docket(DocumentationType.SWAGGER_2)
         // Configura os pacotes que devem ser escaneados para endpoints da API
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.serratec.curriculo"))
          .paths(PathSelectors.any())
          .build()
           // Configura as informações da API, como título, descrição e versão
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() { // Método privado para configurar as informações da API
        return new ApiInfoBuilder()
                .title("Auto Magicamente")
                .description("Documentação da API da minha aplicação Spring Boot")
                .version("1.0")
                .build();
    }
}