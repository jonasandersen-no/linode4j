package com.bjoggis.linode4j.adapter.in;

import com.bjoggis.linode4j.application.usecase.AttachVolumeUseCase;
import com.bjoggis.linode4j.application.usecase.CreateInstanceUseCase;
import com.bjoggis.linode4j.application.usecase.DeleteInstanceUseCase;
import com.bjoggis.linode4j.application.usecase.FindVolumeUseCase;
import com.bjoggis.linode4j.application.usecase.ListInstancesUseCase;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.web.CreateInstanceRequest;
import com.bjoggis.linode4j.web.CreateInstanceResponse;
import com.bjoggis.linode4j.web.ListInstanceResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class InstanceController {

  private final Logger logger = LoggerFactory.getLogger(InstanceController.class);

  private final CreateInstanceUseCase createInstanceUseCase;
  private final ListInstancesUseCase listInstancesUseCase;
  private final DeleteInstanceUseCase deleteInstanceUseCase;
  private final AttachVolumeUseCase attachVolumeUseCase;
  private final FindVolumeUseCase findVolumeUseCase;

  @Value("${linode.volume-id}")
  private Long volumeId;

  public InstanceController(CreateInstanceUseCase createInstanceUseCase,
      ListInstancesUseCase listInstancesUseCase, DeleteInstanceUseCase deleteInstanceUseCase,
      AttachVolumeUseCase attachVolumeUseCase, FindVolumeUseCase findVolumeUseCase) {
    this.createInstanceUseCase = createInstanceUseCase;
    this.listInstancesUseCase = listInstancesUseCase;
    this.deleteInstanceUseCase = deleteInstanceUseCase;
    this.attachVolumeUseCase = attachVolumeUseCase;
    this.findVolumeUseCase = findVolumeUseCase;
  }

  @PostMapping("/create")
  public CreateInstanceResponse createInstance(@RequestBody CreateInstanceRequest request)
      throws InstanceNotFoundException {
    logger.info("{} is creating a new instance", request.createdBy());

    Instance created = createInstanceUseCase.createInstance();

    findVolumeUseCase.findVolume(VolumeId.of(volumeId));

    attachVolumeUseCase.linkVolume(created.getId(), VolumeId.of(volumeId));

    return new CreateInstanceResponse(request.createdBy(), created.getLabel(),
        created.getIp());
  }

  @GetMapping("/list")
  public List<ListInstanceResponse> listInstances() {
    List<Instance> instances = listInstancesUseCase.listInstances();
    return instances.stream()
        .map(ListInstanceResponse::fromDomain)
        .toList();
  }

  @DeleteMapping("{id}")
  public void deleteInstance(@PathVariable Long id) {
    deleteInstanceUseCase.deleteInstance(LinodeId.of(id));
  }
}
