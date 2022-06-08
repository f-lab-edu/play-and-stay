package com.world.playstay.global.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("v1")
        .packagesToScan("com.world")
        .build();
  }

  @Bean
  public OpenAPI springOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Play and Stay").description("Play and Stay API document")
            .version("v0.0.1"));
  }
}