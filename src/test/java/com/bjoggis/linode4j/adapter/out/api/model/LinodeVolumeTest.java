package com.bjoggis.linode4j.adapter.out.api.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class LinodeVolumeTest {

  @Test
  void toDomain() {
    LinodeVolume volume = Instancio.create(LinodeVolume.class);

    Volume domain = volume.toDomain();

    assertThat(domain.getId()).isEqualTo(VolumeId.of((long) volume.id()));
    assertThat(domain.getLabel()).isEqualTo(volume.label());
    assertThat(domain.getStatus()).isEqualTo(volume.status());
    assertThat(domain.getLinodeId()).isEqualTo(LinodeId.of((long) volume.linodeId()));
  }
}