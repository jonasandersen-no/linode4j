package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ListInstancesUseCase {

  private static final Logger logger = LoggerFactory.getLogger(ListInstancesUseCase.class);
  private final LinodeApi api;

  public ListInstancesUseCase(LinodeApi api) {
    this.api = api;
  }

  public List<Instance> listInstances() {
    logger.debug("Listing linode instances");
    final List<Instance> instances = api.listInstances();
    logger.info("Found {} linode instances", instances.size());
    return instances;
  }
}
