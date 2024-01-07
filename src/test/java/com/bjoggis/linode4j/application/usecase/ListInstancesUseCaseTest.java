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

class ListInstancesUseCaseTest {

  @Test
  void listInstances() {

    ListInstancesUseCase useCase = new ListInstancesUseCase(new LinodeApi() {
      @Override
      public Instance createInstance() {
        return null;
      }

      @Override
      public List<Instance> listInstances() {
        return List.of(Instancio.create(Instance.class));
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
    });

    List<Instance> instances = useCase.listInstances();

    assertThat(instances).isNotEmpty();
  }
}