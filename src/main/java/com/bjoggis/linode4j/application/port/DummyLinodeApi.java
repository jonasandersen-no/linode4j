package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DummyLinodeApi implements LinodeApi {

  private final List<Instance> instances = new ArrayList<>();

  public void addInstance(Instance instance) {
    instances.add(instance);
  }

  @Override
  public Instance createInstance() {
    Instance instance = new Instance(LinodeId.of(1L),
        "minecraft-auto-config-1",
        "127.0.0.1", "running", LocalDateTime.now());
    instances.add(instance);
    return instance;
  }


  @Override
  public List<Instance> listInstances() {
    return instances;
  }

  @Override
  public void delete(LinodeId id) {
    instances.removeIf(instance -> instance.getId() == id);
  }
}
