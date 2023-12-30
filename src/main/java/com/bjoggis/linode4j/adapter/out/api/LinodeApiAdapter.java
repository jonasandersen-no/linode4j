package com.bjoggis.linode4j.adapter.out.api;

import com.bjoggis.linode4j.LinodeProperties;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class LinodeApiAdapter implements LinodeApi {

  private final LinodeInterface linodeInterface;
  private final LinodeProperties properties;

  LinodeApiAdapter(LinodeInterface linodeInterface, LinodeProperties properties) {
    this.linodeInterface = linodeInterface;
    this.properties = properties;
  }

  @Override
  public Instance createInstance() {
    final CreateInstanceRequestBody body = new CreateInstanceRequestBody();
    body.setRegion("se-sto");
    body.setImage("linode/ubuntu22.04");
    body.setLabel("minecraft-auto-config-%d".formatted(System.currentTimeMillis()));
    body.setType("g6-standard-2");
    body.setTags(List.of("minecraft", "auto-created"));
    body.setRootPassword((properties.rootPassword()));

    LinodeInstance linodeInstance = linodeInterface.create(body);

    return linodeInstance.toDomain();
  }
}
