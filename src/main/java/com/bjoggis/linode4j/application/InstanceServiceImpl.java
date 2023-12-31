package com.bjoggis.linode4j.application;

import com.bjoggis.linode4j.application.port.InstanceService;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

@Service
class InstanceServiceImpl implements InstanceService {

  private final Logger logger = LoggerFactory.getLogger(InstanceServiceImpl.class);
  private final LinodeApi api;

  InstanceServiceImpl(LinodeApi api) {
    this.api = api;
  }

  @Override
  public Instance createInstance() {
    Instance instance = api.createInstance();
    logger.info("Created linode instance: {}", instance);
    return instance;
  }

  @Override
  public List<Instance> listInstances() {
    return api.listInstances();
  }

  @Override
  public void deleteInstance(LinodeId id) {
    logger.info("Deleting instance {}", id);

    api.listInstances().stream()
        .filter(instance -> instance.getId().equals(id))
        .findFirst()
        .ifPresentOrElse(instance -> {
          api.delete(id);
          logger.info("Deleted instance {}", id);
        }, () -> {
          logger.info("Instance {} not found, not deleting", id);
          ProblemDetail pd = ProblemDetail
              .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Instance not found");
          pd.setTitle("Instance not found");
          throw new ErrorResponseException(HttpStatus.BAD_REQUEST, pd, null);
        });
  }
}
