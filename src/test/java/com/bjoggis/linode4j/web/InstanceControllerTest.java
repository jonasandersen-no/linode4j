package com.bjoggis.linode4j.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bjoggis.linode4j.TestSetup;
import com.bjoggis.linode4j.api.LinodeInterface;
import com.bjoggis.linode4j.api.model.LinodeInstance;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class InstanceControllerTest extends TestSetup {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  LinodeInterface linodeInterface;

  @Test
  void testInstanceCreated() throws Exception {
    var request = new CreateInstanceRequest("testname");

    var expectedResponse = new CreateInstanceResponse("testname", "127.0.0.1");

    mvc.perform(post("/instance/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            objectMapper.writeValueAsString(expectedResponse)));
  }
}