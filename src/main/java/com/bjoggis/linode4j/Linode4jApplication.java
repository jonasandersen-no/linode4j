package com.bjoggis.linode4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Linode4jApplication {

  public static void main(String[] args) {
    SpringApplication.run(Linode4jApplication.class, args);
  }

  @GetMapping
  public String hello() {
    return "Hello World!";
  }

}
