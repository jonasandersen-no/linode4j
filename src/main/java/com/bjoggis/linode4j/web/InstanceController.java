package com.bjoggis.linode4j.web;

import com.bjoggis.linode4j.LinodeProperties;
import com.bjoggis.linode4j.adapter.out.api.CreateInstanceRequestBody;
import com.bjoggis.linode4j.adapter.out.api.LinodeInterface;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;
import com.bjoggis.linode4j.application.port.InstanceRepository;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instance")
public class InstanceController {

  private final Logger logger = LoggerFactory.getLogger(InstanceController.class);
  private final LinodeInterface api;
  private final LinodeProperties properties;
  private final InstanceRepository instanceRepository;

  public InstanceController(LinodeInterface api, LinodeProperties properties,
      InstanceRepository instanceRepository) {
    this.api = api;
    this.properties = properties;
    this.instanceRepository = instanceRepository;
  }

  @PostMapping("/create")
  public CreateInstanceResponse createInstance(@RequestBody CreateInstanceRequest request) {
    logger.info("{} is creating a new instance", request.createdBy());

    final CreateInstanceRequestBody body = new CreateInstanceRequestBody();
    body.setRegion("se-sto");
    body.setImage("linode/ubuntu22.04");
    body.setLabel("minecraft-auto-config-%d".formatted(System.currentTimeMillis()));
    body.setType("g6-standard-2");
    body.setTags(List.of("minecraft", "auto-created"));
    body.setRootPassword((properties.rootPassword()));

    LinodeInstance created = api.create(body);
    logger.info("Created linode instance: {}", created);

    Instance instance = new Instance(LinodeId.of(created.id()), created.label(),
        created.ipv4().getFirst(),
        created.status(),
        created.created());

    instanceRepository.save(instance);

    return new CreateInstanceResponse(request.createdBy(), created.label(),
        created.ipv4().getFirst());
  }

  @GetMapping("/list")
  public List<ListInstanceResponse> listInstances() {
    List<LinodeInstance> instances = api.list(1, 100).data();

    List<LinodeInstance> filteredList = instances.stream()
        .filter(instance -> instance.tags().contains("auto-created"))
        .toList();

    //Update status in db
    filteredList.forEach(instance -> {
      Optional<Instance> foundOpt = instanceRepository.findByLinodeId(LinodeId.of(instance.id()));
      if (foundOpt.isPresent()) {
        Instance found = foundOpt.get();
        found.setStatus(instance.status());
        instanceRepository.save(found);
      } else {
        Instance newInstance = new Instance(LinodeId.of(instance.id()), instance.label(),
            instance.ipv4().getFirst(),
            instance.status(), instance.created());
        instanceRepository.save(newInstance);
      }
    });

    return filteredList.stream()
        .map(instance -> new ListInstanceResponse(instance.id(), instance.label(),
            instance.ipv4().getFirst(),
            instance.status(), instance.created()))
        .toList();
  }

  @DeleteMapping("{id}")
  @Transactional
  public void deleteInstance(@PathVariable Long id, @RequestParam String deletedBy) {
    logger.info("{} is requesting to delete instance {}", deletedBy, id);

    api.list(1, 100).data().stream()
        .filter(instance -> instance.id() == id)
        .findFirst()
        .ifPresentOrElse(instance -> {
          if (instance.tags().contains("auto-created")) {
            logger.info("Instance {} is auto-created, deleting", id);
            api.delete(id);
            logger.info("Deleted instance {}", id);
          } else {
            logger.info("Instance {} is not auto-created, not deleting", id);
            ProblemDetail pd = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    "Instance is not auto-created");
            pd.setTitle("Instance is not auto-created");
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, pd, null);
          }
        }, () -> {
          logger.info("Instance {} not found, not deleting", id);
          ProblemDetail pd = ProblemDetail
              .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Instance not found");
          pd.setTitle("Instance not found");
          throw new ErrorResponseException(HttpStatus.BAD_REQUEST, pd, null);
        });
    instanceRepository.deleteByLinodeId(LinodeId.of(id));
  }
}
