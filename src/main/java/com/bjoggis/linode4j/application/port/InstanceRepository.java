package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.Optional;

public interface InstanceRepository {

  Optional<Instance> findByLinodeId(LinodeId linodeId);

  void deleteByLinodeId(LinodeId linodeId);

  void save(Instance linodeInstance);

  void flush();
}
