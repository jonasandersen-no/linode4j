package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.List;

public interface LinodeApi {

  Instance createInstance();

  List<Instance> listInstances();

  void delete(LinodeId id);
}
