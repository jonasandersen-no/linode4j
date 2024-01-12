package com.bjoggis.linode4j.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class ListVolumeUseCaseTest {

  @Test
  void listVolume() {
    ListVolumeUseCase useCase = new ListVolumeUseCase(new TestLinodeApi());

    List<Volume> volumes = useCase.listVolume();

    assertThat(volumes).hasSize(1);
  }

  private static class TestLinodeApi implements LinodeApi {

    @Override
    public Instance createInstance() {
      return null;
    }

    @Override
    public List<Instance> listInstances() {
      return null;
    }

    @Override
    public void delete(LinodeId id) {

    }

    @Override
    public List<Volume> findVolumes() {
      return Instancio.ofList(Volume.class)
          .size(1)
          .create();
    }

    @Override
    public Volume linkVolume(Instance instance, VolumeId volumeId) {
      return null;
    }
  }
}