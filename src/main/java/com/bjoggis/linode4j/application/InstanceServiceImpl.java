package com.bjoggis.linode4j.application;

import com.bjoggis.linode4j.application.port.InstanceService;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.domain.VolumeNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

@Service
public class InstanceServiceImpl implements InstanceService {

  private final Logger logger = LoggerFactory.getLogger(InstanceServiceImpl.class);
  private final LinodeApi api;

  @Value("${linode.volume-id}")
  private Long volumeId;

  public InstanceServiceImpl(LinodeApi api) {
    this.api = api;
  }

  @Override
  public Instance createInstance() {
    Instance instance = api.createInstance();
    logger.info("Created linode instance: {}", instance);

    try {
      linkVolume(instance.getId(), VolumeId.of(volumeId));
    } catch (InstanceNotFoundException e) {
      throw new RuntimeException(e);
    }
    return instance;
  }

  @Override
  public Instance findInstance(LinodeId id) throws InstanceNotFoundException {
    return api.listInstances().stream()
        .filter(instance -> instance.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new InstanceNotFoundException(id));
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

  @Override
  public void linkVolume(LinodeId id, VolumeId volumeId) throws InstanceNotFoundException {
    logger.info("Linking volume {} to instance {}", volumeId, id);

    Instance instance = findInstance(id);

    Volume volume = findVolume(volumeId);

    api.linkVolume(instance, volume.getId());
  }

  @Override
  public Volume findVolume(VolumeId volumeId) throws VolumeNotFoundException {
    return api.findVolumes().stream()
        .filter(volume -> volume.getId().equals(volumeId))
        .findFirst()
        .orElseThrow(() -> new VolumeNotFoundException(volumeId));
  }
}
