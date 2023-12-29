package com.bjoggis.linode4j.web;

import com.bjoggis.linode4j.LinodeProperties;
import com.bjoggis.linode4j.api.CreateInstanceRequestBody;
import com.bjoggis.linode4j.api.LinodeInterface;
import com.bjoggis.linode4j.api.model.LinodeInstance;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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

  public InstanceController(LinodeInterface api, LinodeProperties properties) {
    this.api = api;
    this.properties = properties;
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

    return new CreateInstanceResponse(request.createdBy(), created.label(),
        created.ipv4().getFirst());
  }

  @GetMapping("/list")
  public List<ListInstanceResponse> listInstances() {
    List<LinodeInstance> instances = api.list(1, 100).data();

    return instances.stream()
        .filter(instance -> instance.tags().contains("auto-created"))
        .map(instance -> new ListInstanceResponse(instance.id(), instance.label(),
            instance.ipv4().getFirst(),
            instance.status(), instance.created()))
        .toList();
  }

  @DeleteMapping("{id}")
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
  }
}
