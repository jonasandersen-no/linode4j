package com.bjoggis.linode4j.adapter.out.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.TestSetup;
import com.bjoggis.linode4j.domain.Instance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LinodeApiAdapterTest extends TestSetup {

  @Autowired
  LinodeApiAdapter adapter;

  @Test
  void createInstance() {
    Instance instance = adapter.createInstance();

    assertThat(instance).isNotNull();
  }
}