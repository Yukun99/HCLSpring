package com.spring;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:com/spring/customers.xml",
    "classpath:com/spring/customerRepository.xml"})
public class Main {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Main.class);
    app.setDefaultProperties(Collections.singletonMap("spring.config.location",
        "classpath:/com/spring/application.properties")
    );
    app.run(args);
  }
}