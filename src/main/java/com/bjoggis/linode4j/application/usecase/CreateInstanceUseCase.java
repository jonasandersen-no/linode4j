package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.InstanceServiceImpl;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateInstanceUseCase {

  private static final Logger logger = LoggerFactory.getLogger(InstanceServiceImpl.class);
  private final LinodeApi api;

  public CreateInstanceUseCase(LinodeApi api) {
    this.api = api;
  }


  public Instance createInstance() {
    Instance instance = api.createInstance();
    logger.info("Created linode instance: {}", instance);
    return instance;
  }
}
