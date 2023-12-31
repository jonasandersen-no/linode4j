package com.bjoggis.linode4j.web;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bjoggis.linode4j.TestSetup;
import com.bjoggis.linode4j.application.port.DummyLinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
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
  private DummyLinodeApi dummyLinodeApi;

  @Test
  void instanceCreated() throws Exception {
    var request = new CreateInstanceRequest("admin");

    mvc.perform(post("/instance/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
            .with(csrf()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.createdBy").value("admin"))
        .andExpect(jsonPath("$.label").value(startsWith("minecraft-auto-config-")));
  }

  @Test
  void listInstances() throws Exception {
    createAndSaveInstance();

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
    LinodeId linodeId = createAndSaveInstance();

    mvc.perform(delete("/instance/%d".formatted(linodeId.id()))
            .with(csrf()))
        .andDo(print())
        .andExpect(status().isOk());
  }


  Long ID_SEQ = 1L;

  private LinodeId createAndSaveInstance() {
    Long nextId = ID_SEQ;
    ID_SEQ += 1;
    LinodeId linodeId = LinodeId.of(nextId);

    Instance instance = new Instance(
        linodeId, "minecraft-auto-config-123",
        "127.0.0.1", "running", LocalDateTime.now());

    dummyLinodeApi.addInstance(instance);

    return linodeId;
  }
}