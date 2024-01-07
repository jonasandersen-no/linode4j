package com.bjoggis.linode4j.application.usecase;

import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.LinodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;

@Component
public class DeleteInstanceUseCase {

  private static final Logger logger = LoggerFactory.getLogger(DeleteInstanceUseCase.class);
  private final LinodeApi api;

  public DeleteInstanceUseCase(LinodeApi api) {
    this.api = api;
  }

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
