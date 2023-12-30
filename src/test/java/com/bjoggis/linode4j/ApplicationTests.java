package com.bjoggis.linode4j;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.api.DummyLinodeInterface;
import com.bjoggis.linode4j.adapter.out.api.LinodeInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ApplicationTests extends TestSetup {

  @Autowired
  private LinodeInterface linodeInterface;

  @Test
  void contextLoads() {

    assertThat(linodeInterface instanceof DummyLinodeInterface).isTrue();
  }

}
