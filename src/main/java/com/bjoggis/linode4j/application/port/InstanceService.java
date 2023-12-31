package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.List;

public interface InstanceService {

  Instance createInstance();

  List<Instance> listInstances();

  void deleteInstance(LinodeId id);
}
