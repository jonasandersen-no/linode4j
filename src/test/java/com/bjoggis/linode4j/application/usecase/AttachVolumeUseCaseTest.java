package com.bjoggis.linode4j.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import java.util.List;
import org.junit.jupiter.api.Test;

class AttachVolumeUseCaseTest {

  @Test
  void linkVolume() throws InstanceNotFoundException {
    AttachVolumeUseCase useCase = new AttachVolumeUseCase(new LinodeApiStub());

    Volume volume = useCase.linkVolume(LinodeId.of(1L), VolumeId.of(1L));

    assertThat(volume).isNotNull();
    assertThat(volume.getId()).isEqualTo(VolumeId.of(1L));
    assertThat(volume.getLinodeId()).isEqualTo(LinodeId.of(1L));
  }

  private static class LinodeApiStub implements LinodeApi {

    private final Instance instance = new Instance(LinodeId.of(1L));
    private final Volume volume = new Volume(VolumeId.of(1L));

    @Override
    public Instance createInstance() {
      return null;
    }

    @Override
    public List<Instance> listInstances() {
      return List.of(instance);
    }

    @Override
    public void delete(LinodeId id) {

    }

    @Override
    public List<Volume> findVolumes() {
      return List.of(volume);
    }

    @Override
    public Volume linkVolume(Instance instance, VolumeId volumeId) {
      if (instance.getId().equals(LinodeId.of(1L)) && volumeId.equals(VolumeId.of(1L))) {
        volume.setLinodeId(instance.getId());
        return volume;
      }
      throw new IllegalArgumentException();
    }
  }
}