package com.bjoggis.linode4j.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.domain.Instance;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class ListInstanceResponseTest {

  @Test
  void fromDomain() {
    Instance instance = Instancio.create(Instance.class);

    ListInstanceResponse response = ListInstanceResponse.fromDomain(instance);
    assertThat(response.id()).isEqualTo(instance.getId().id());
    assertThat(response.ip()).isEqualTo(instance.getIp());
    assertThat(response.label()).isEqualTo(instance.getLabel());
    assertThat(response.status()).isEqualTo(instance.getStatus());
    assertThat(response.created()).isEqualTo(instance.getCreated());
  }
}