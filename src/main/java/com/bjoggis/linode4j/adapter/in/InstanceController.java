package com.bjoggis.linode4j.adapter.in;

import com.bjoggis.linode4j.application.port.InstanceService;
import com.bjoggis.linode4j.application.usecase.CreateInstanceUseCase;
import com.bjoggis.linode4j.application.usecase.DeleteInstanceUseCase;
import com.bjoggis.linode4j.application.usecase.ListInstancesUseCase;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.web.CreateInstanceRequest;
import com.bjoggis.linode4j.web.CreateInstanceResponse;
import com.bjoggis.linode4j.web.ListInstanceResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private final InstanceService service;

  private final CreateInstanceUseCase createInstanceUseCase;
  private final ListInstancesUseCase listInstancesUseCase;
  private final DeleteInstanceUseCase deleteInstanceUseCase;

  public InstanceController(InstanceService service, CreateInstanceUseCase createInstanceUseCase,
      ListInstancesUseCase listInstancesUseCase, DeleteInstanceUseCase deleteInstanceUseCase) {
    this.service = service;
    this.createInstanceUseCase = createInstanceUseCase;
    this.listInstancesUseCase = listInstancesUseCase;
    this.deleteInstanceUseCase = deleteInstanceUseCase;
  }

  @PostMapping("/create")
  public CreateInstanceResponse createInstance(@RequestBody CreateInstanceRequest request) {
    logger.info("{} is creating a new instance", request.createdBy());

    Instance created = createInstanceUseCase.createInstance();

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
