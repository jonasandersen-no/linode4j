package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import org.springframework.stereotype.Component;

@Component
public class FindInstanceUseCase {

  private final LinodeApi api;

  public FindInstanceUseCase(LinodeApi api) {
    this.api = api;
  }

  public Instance findInstance(LinodeId id) throws InstanceNotFoundException {
    return api.listInstances().stream()
        .filter(instance -> instance.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new InstanceNotFoundException(id));
  }

}
