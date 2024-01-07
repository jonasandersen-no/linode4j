package com.bjoggis.linode4j.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VolumeIdTest {

  @Test
  void of() {
    assertThat(VolumeId.of(1L).id()).isEqualTo(1L);
  }
}