package com.bjoggis.linode4j;

import com.bjoggis.linode4j.TestSetup.TestConfig;
import com.bjoggis.linode4j.api.DummyLinodeInterface;
import com.bjoggis.linode4j.api.LinodeInterface;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest(classes = {Application.class, TestConfig.class})
@AutoConfigureMockMvc
public abstract class TestSetup {

  @TestConfiguration
  public static class TestConfig {

    @Bean
    LinodeInterface linodeInterface() {
      return new DummyLinodeInterface();
    }
  }
}
