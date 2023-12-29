package com.bjoggis.linode4j.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class InstanceController {

  private final Logger logger = LoggerFactory.getLogger(InstanceController.class);

  @PostMapping("/create")
  public CreateInstanceResponse createInstance(@RequestBody CreateInstanceRequest request) {
    logger.info("Creating instance with name: {}", request.name());

    return new CreateInstanceResponse(request.name(), "127.0.0.1");
  }

}
