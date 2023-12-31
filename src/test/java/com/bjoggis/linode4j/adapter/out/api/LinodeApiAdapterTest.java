package com.bjoggis.linode4j.adapter.out.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.Application;
import com.bjoggis.linode4j.LinodeProperties;
import com.bjoggis.linode4j.api.DummyLinodeInterface;
import com.bjoggis.linode4j.domain.Instance;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Application.class})
@Tag("integration")
class LinodeApiAdapterTest {

  @Autowired
  LinodeProperties properties;

  @Test
  void createInstance() {
    LinodeApiAdapter adapter = new LinodeApiAdapter(new DummyLinodeInterface(), properties);

    Instance instance = adapter.createInstance();

    assertThat(instance).isNotNull();
  }
}