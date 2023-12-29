package com.bjoggis.linode4j.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bjoggis.linode4j.TestSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class InstanceControllerTest extends TestSetup {

  @Autowired
  private MockMvc mvc;

  @Test
  void testInstanceCreated() throws Exception {
    mvc.perform(post("/instance/create"))
        .andExpect(status().isOk())
        .andExpect(content().string("Instance created!"));
  }
}