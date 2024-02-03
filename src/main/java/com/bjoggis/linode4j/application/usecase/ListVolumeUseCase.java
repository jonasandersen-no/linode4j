package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Tag;
import com.bjoggis.linode4j.domain.Volume;
import jakarta.annotation.Nullable;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ListVolumeUseCase {

  private final LinodeApi api;

  public ListVolumeUseCase(LinodeApi api) {
    this.api = api;
  }

  public List<Volume> listVolume() {
    return listVolume(null);
  }

  /**
   * List volumes with a specific tag.
   *
   * @param tag the tag to filter on
   * @return a list of volumes with the given tag
   */
  public List<Volume> listVolume(@Nullable Tag tag) {
    return api.findVolumes();
  }
}
