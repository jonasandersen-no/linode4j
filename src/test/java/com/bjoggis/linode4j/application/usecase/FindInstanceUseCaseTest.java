package com.bjoggis.linode4j.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import java.util.List;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;

class FindInstanceUseCaseTest {

  @Test
  void findInstance() throws InstanceNotFoundException {
    FindInstanceUseCase useCase = new FindInstanceUseCase(new LinodeApiStub());

    Instance instance = useCase.findInstance(LinodeId.of(1L));

    assertThat(instance).isNotNull();
    assertThat(instance.getId()).isEqualTo(LinodeId.of(1L));

  }

  @Test
  void findNonExistingInstanceThrowsException() {
    FindInstanceUseCase useCase = new FindInstanceUseCase(new LinodeApiStub());

    assertThrows(InstanceNotFoundException.class, () -> {
      useCase.findInstance(LinodeId.of(2L));
    });
  }

  private static class LinodeApiStub implements LinodeApi {

    @Override
    public Instance createInstance() {
      return null;
    }

    @Override
    public List<Instance> listInstances() {
      return List.of(Instancio.of(Instance.class)
          .set(Select.field(Instance::getId), LinodeId.of(1L))
          .create());
    }

    @Override
    public void delete(LinodeId id) {

    }

    @Override
    public List<Volume> findVolumes() {
      return null;
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