package com.world.playstay;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PlaystayApplication {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args) {
    SpringApplication.run(PlaystayApplication.class, args);
  }


  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      logger.debug("Let's inspect the beans provided by Spring Boot:");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        logger.debug("BEAN: {}", beanName);
      }

    };
  }
}
