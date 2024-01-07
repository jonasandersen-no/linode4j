package com.bjoggis.linode4j.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LinodeIdTest {

  @Test
  void of() {
    LinodeId linodeId = LinodeId.of(1L);

    assertThat(linodeId.id()).isEqualTo(1L);
  }
}