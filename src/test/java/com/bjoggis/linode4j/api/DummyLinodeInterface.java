package com.bjoggis.linode4j.api;


import com.bjoggis.linode4j.adapter.out.api.AttachVolumeRequestBody;
import com.bjoggis.linode4j.adapter.out.api.CreateInstanceRequestBody;
import com.bjoggis.linode4j.adapter.out.api.LinodeInterface;
import com.bjoggis.linode4j.adapter.out.api.model.InstanceType;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeVolume;
import com.bjoggis.linode4j.adapter.out.api.model.Page;
import com.bjoggis.linode4j.adapter.out.api.model.Region;
import java.util.List;
import org.instancio.Instancio;
import org.instancio.Select;

public class DummyLinodeInterface implements LinodeInterface {

  @Override
  public Page<LinodeInstance> list(Integer page, Integer pageSize) {
    return new Page<>(List.of(Instancio.of(LinodeInstance.class)
        .set(Select.field("id"), 1)
        .set(Select.field("tags"), List.of("auto-created"))
        .set(Select.field("label"),
            "minecraft-auto-config-%d".formatted(System.currentTimeMillis()))
        .set(Select.field("ipv4"), List.of("127.0.0.1")).create()), 1, 1, 1);
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

  @Override
  public Page<LinodeVolume> volumes() {
    return null;
  }

  @Override
  public LinodeVolume attach(Long volumeId, AttachVolumeRequestBody body) {
    return null;
  }
}