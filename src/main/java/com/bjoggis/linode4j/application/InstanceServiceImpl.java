package com.bjoggis.linode4j.application;

import com.bjoggis.linode4j.LinodeProperties;
import com.bjoggis.linode4j.adapter.in.InstanceController;
import com.bjoggis.linode4j.adapter.out.api.CreateInstanceRequestBody;
import com.bjoggis.linode4j.adapter.out.api.LinodeInterface;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;
import com.bjoggis.linode4j.application.port.InstanceRepository;
import com.bjoggis.linode4j.application.port.InstanceService;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class InstanceServiceImpl implements InstanceService {

  private final Logger logger = LoggerFactory.getLogger(InstanceController.class);
  private final LinodeInterface api;
  private final LinodeProperties properties;
  private final InstanceRepository instanceRepository;

  InstanceServiceImpl(LinodeInterface api, LinodeProperties properties,
      InstanceRepository instanceRepository) {
    this.api = api;
    this.properties = properties;
    this.instanceRepository = instanceRepository;
  }

  @Override
  public LinodeInstance createInstance() {
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
    return created;
  }
}
