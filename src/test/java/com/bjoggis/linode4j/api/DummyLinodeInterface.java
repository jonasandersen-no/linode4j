package com.bjoggis.linode4j.api;


import com.bjoggis.linode4j.api.model.InstanceType;
import com.bjoggis.linode4j.api.model.LinodeInstance;
import com.bjoggis.linode4j.api.model.Page;
import com.bjoggis.linode4j.api.model.Region;
import java.util.List;
import org.instancio.Instancio;
import org.instancio.Select;

public class DummyLinodeInterface implements LinodeInterface {

  @Override
  public Page<LinodeInstance> list(Integer page, Integer pageSize) {
    return null;
  }

  @Override
  public Page<InstanceType> types() {
    return null;
  }

  @Override
  public Page<Region> regions() {
    return null;
  }

  @Override
  public LinodeInstance create(CreateInstanceRequestBody instance) {
    return Instancio.of(LinodeInstance.class)
        .set(Select.field("label"), instance.getLabel())
        .set(Select.field("ipv4"), List.of("127.0.0.1"))
        .create();
  }

  @Override
  public LinodeInstance getInstance(Long linodeId) {
    return null;
  }

  @Override
  public void delete(Long linodeId) {

  }
}