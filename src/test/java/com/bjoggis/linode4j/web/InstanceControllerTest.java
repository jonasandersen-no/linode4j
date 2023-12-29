package com.bjoggis.linode4j.web;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bjoggis.linode4j.TestSetup;
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

  @Test
  void instanceCreated() throws Exception {
    var request = new CreateInstanceRequest("admin");

    mvc.perform(post("/instance/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.createdBy").value("admin"))
        .andExpect(jsonPath("$.label").value(startsWith("minecraft-auto-config-")));
  }

  @Test
  void listInstances() throws Exception {
    mvc.perform(get("/instance/list"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").isNumber())
        .andExpect(jsonPath("$[0].label").value(startsWith("minecraft-auto-config-")))
        .andExpect(jsonPath("$[0].ip").exists())
        .andExpect(jsonPath("$[0].status").exists())
        .andExpect(jsonPath("$[0].created").exists());
  }

  @Test
  void deleteInstance() throws Exception {
    mvc.perform(delete("/instance/1")
            .param("deletedBy", "admin"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}