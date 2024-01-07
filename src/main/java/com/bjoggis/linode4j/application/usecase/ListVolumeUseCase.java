package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Volume;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListVolumeUseCase {

  private final LinodeApi api;

  public ListVolumeUseCase(LinodeApi api) {
    this.api = api;
  }

  public List<Volume> listVolume() {
    return api.findVolumes();
  }
}
