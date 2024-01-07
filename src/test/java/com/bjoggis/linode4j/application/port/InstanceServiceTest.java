package com.bjoggis.linode4j.application.port;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bjoggis.linode4j.application.InstanceServiceImpl;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.domain.VolumeNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class InstanceServiceTest {

  @Nested
  class LinkLinodeVolume {

    @Test
    @Disabled
    void linkVolumeAttachesVolumeToInstance() throws InstanceNotFoundException {
      DummyLinodeApi api = new DummyLinodeApi();
      InstanceService service = new InstanceServiceImpl(api);


//      service.linkVolume(instance.getId(), VolumeId.of(2L));
    }

    @Test
    @Disabled
    void linkNonExistingVolumeThrowsException() {
      DummyLinodeApi api = new DummyLinodeApi();
      InstanceService service = new InstanceServiceImpl(api);


      assertThrows(VolumeNotFoundException.class, () -> {
        service.linkVolume(LinodeId.of(1L), VolumeId.of(2L));
      });
    }

    @Test
    void linkNonExistingInstanceThrowsException() {
      DummyLinodeApi api = new DummyLinodeApi();
      InstanceService service = new InstanceServiceImpl(api);

      assertThrows(InstanceNotFoundException.class, () -> {
        service.linkVolume(LinodeId.of(1L), VolumeId.of(2L));
      });
    }
  }
}