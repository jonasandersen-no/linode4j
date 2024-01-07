package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.domain.VolumeNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FindVolumeUseCase {

  private final LinodeApi api;

  public FindVolumeUseCase(LinodeApi api) {
    this.api = api;
  }

  public Volume findVolume(VolumeId volumeId) throws VolumeNotFoundException {
    return api.findVolumes().stream()
        .filter(volume -> volume.getId().equals(volumeId))
        .findFirst()
        .orElseThrow(() -> new VolumeNotFoundException(volumeId));
  }

}
