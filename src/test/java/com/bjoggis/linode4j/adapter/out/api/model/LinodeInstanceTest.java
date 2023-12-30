package com.bjoggis.linode4j.adapter.out.api.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.domain.Instance;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class LinodeInstanceTest {

  @Test
  void toDomain() {
    LinodeInstance linodeInstance = Instancio.create(LinodeInstance.class);

    Instance domain = linodeInstance.toDomain();

    assertThat(domain.getId().id()).isEqualTo(linodeInstance.id());
    assertThat(domain.getIp()).isEqualTo(linodeInstance.ipv4().getFirst());
    assertThat(domain.getLabel()).isEqualTo(linodeInstance.label());
    assertThat(domain.getStatus()).isEqualTo(linodeInstance.status());
    assertThat(domain.getCreated()).isEqualTo(linodeInstance.created());
  }
}