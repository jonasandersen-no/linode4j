package com.bjoggis.linode4j.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class InstanceController {

  @PostMapping("/create")
  public String createInstance() {
    return "Instance created!";
  }
}
