package com.bjoggis.linode4j.adapter.in;

import com.bjoggis.linode4j.application.usecase.AttachVolumeUseCase;
import com.bjoggis.linode4j.application.usecase.ListVolumeUseCase;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.VolumeId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volume")
public class VolumeController {

  private final ListVolumeUseCase listVolumeUseCase;
  private final AttachVolumeUseCase attachVolumeUseCase;

  public VolumeController(ListVolumeUseCase listVolumeUseCase,
      AttachVolumeUseCase attachVolumeUseCase) {
    this.listVolumeUseCase = listVolumeUseCase;
    this.attachVolumeUseCase = attachVolumeUseCase;
  }

  @GetMapping
  String listVolumes() {
    return listVolumeUseCase.listVolume().toString();
  }

  @PatchMapping
  void attachVolume(@RequestParam Long volumeId, @RequestParam Long linodeId)
      throws InstanceNotFoundException {
    attachVolumeUseCase.linkVolume(LinodeId.of(linodeId), VolumeId.of(volumeId));
  }

  @PatchMapping("/detach")
  void detachVolume(@RequestParam Long volumeId) {
    attachVolumeUseCase.unlinkVolume(VolumeId.of(volumeId));
  }
}
