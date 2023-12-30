package com.bjoggis.linode4j.application;

import com.bjoggis.linode4j.application.port.InstanceRepository;
import com.bjoggis.linode4j.application.port.InstanceService;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class InstanceServiceImpl implements InstanceService {

  private final Logger logger = LoggerFactory.getLogger(InstanceServiceImpl.class);
  private final LinodeApi api;
  private final InstanceRepository instanceRepository;

  InstanceServiceImpl(LinodeApi api,
      InstanceRepository instanceRepository) {
    this.api = api;
    this.instanceRepository = instanceRepository;
  }

  @Override
  public Instance createInstance() {
    Instance instance = api.createInstance();
    logger.info("Created linode instance: {}", instance);
    instanceRepository.save(instance);
    return instance;
  }
}
