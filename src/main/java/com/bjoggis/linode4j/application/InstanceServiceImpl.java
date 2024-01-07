package com.bjoggis.linode4j.application;

import com.bjoggis.linode4j.application.port.InstanceService;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.application.usecase.FindInstanceUseCase;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.domain.VolumeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InstanceServiceImpl implements InstanceService {

  private final Logger logger = LoggerFactory.getLogger(InstanceServiceImpl.class);
  private final LinodeApi api;
  private final FindInstanceUseCase findInstanceUseCase;

  @Value("${linode.volume-id}")
  private Long volumeId;

  public InstanceServiceImpl(LinodeApi api, FindInstanceUseCase findInstanceUseCase) {
    this.api = api;
    this.findInstanceUseCase = findInstanceUseCase;
  }

  @Override
  public void linkVolume(LinodeId id, VolumeId volumeId) throws InstanceNotFoundException {
    logger.info("Linking volume {} to instance {}", volumeId, id);

    Instance instance = findInstanceUseCase.findInstance(id);

    Volume volume = findVolume(volumeId);

    api.linkVolume(instance, volume.getId());
  }

  @Override
  public Volume findVolume(VolumeId volumeId) throws VolumeNotFoundException {
    return api.findVolumes().stream()
        .filter(volume -> volume.getId().equals(volumeId))
        .findFirst()
        .orElseThrow(() -> new VolumeNotFoundException(volumeId));
  }
}
