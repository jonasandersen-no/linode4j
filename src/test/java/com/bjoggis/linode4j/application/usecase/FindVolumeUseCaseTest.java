package com.bjoggis.linode4j.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import java.util.List;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;

class FindVolumeUseCaseTest {

  @Test
  void findVolume() {
    FindVolumeUseCase useCase = new FindVolumeUseCase(new LinodeApiStub());

    Volume volume = useCase.findVolume(VolumeId.of(1L));

    assertThat(volume).isNotNull();

  }

  private static class LinodeApiStub implements LinodeApi {

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
      return List.of(Instancio.of(Volume.class)
          .set(Select.field(Volume::getId), VolumeId.of(1L))
          .create());
    }

    @Override
    public Volume linkVolume(Instance instance, VolumeId volumeId) {
      return null;
    }

    @Override
    public void detachVolume(VolumeId volumeId) {

    }
  }
}