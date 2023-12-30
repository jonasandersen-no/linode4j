package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;

public interface InstanceService {

  LinodeInstance createInstance();
}
