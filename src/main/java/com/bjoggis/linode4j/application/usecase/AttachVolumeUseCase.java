package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.domain.VolumeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AttachVolumeUseCase {

  private static final Logger logger = LoggerFactory.getLogger(AttachVolumeUseCase.class);
  private final LinodeApi api;

  public AttachVolumeUseCase(LinodeApi api) {
    this.api = api;
  }

  public Volume linkVolume(LinodeId id, VolumeId volumeId) throws InstanceNotFoundException {
    logger.info("Linking volume {} to instance {}", volumeId, id);

    Instance instance = findInstance(id);

    Volume volume = findVolume(volumeId);

    return api.linkVolume(instance, volume.getId());
  }

  Volume findVolume(VolumeId volumeId) throws VolumeNotFoundException {
    return api.findVolumes().stream()
        .filter(volume -> volume.getId().equals(volumeId))
        .findFirst()
        .orElseThrow(() -> new VolumeNotFoundException(volumeId));
  }

  Instance findInstance(LinodeId id) throws InstanceNotFoundException {
    return api.listInstances().stream()
        .filter(instance -> instance.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new InstanceNotFoundException(id));
  }
}
