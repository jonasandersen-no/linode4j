package com.bjoggis.linode4j.adapter.out.api;

import com.bjoggis.linode4j.LinodeProperties;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeVolume;
import com.bjoggis.linode4j.adapter.out.api.model.Page;
import com.bjoggis.linode4j.application.port.LinodeApi;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class LinodeApiAdapter implements LinodeApi {

  private final LinodeInterface linodeInterface;
  private final LinodeProperties properties;

  LinodeApiAdapter(LinodeInterface linodeInterface, LinodeProperties properties) {
    this.linodeInterface = linodeInterface;
    this.properties = properties;
  }

  @Override
  public Instance createInstance() {
    final CreateInstanceRequestBody body = new CreateInstanceRequestBody();
    body.setRegion("se-sto");
    body.setImage("linode/ubuntu22.04");
    body.setLabel("minecraft-auto-config-%d".formatted(System.currentTimeMillis()));
    body.setType("g6-standard-2");
    body.setTags(List.of("minecraft", "auto-created"));
    body.setRootPassword((properties.rootPassword()));

    LinodeInstance linodeInstance = linodeInterface.create(body);

    return linodeInstance.toDomain();
  }

  @Override
  public List<Instance> listInstances() {
    Page<LinodeInstance> page = linodeInterface.list(1, 100);

    List<LinodeInstance> data = page.data();

    return data.stream()
        .filter(instance -> instance.tags().contains("auto-created"))
        .map(LinodeInstance::toDomain)
        .toList();

  }

  @Override
  public void delete(LinodeId id) {
    linodeInterface.delete(id.id());
  }

  @Override
  public List<Volume> findVolumes() {
    return linodeInterface.volumes().data()
        .stream()
        .filter(volume -> volume.tags().contains("auto-created"))
        .map(LinodeVolume::toDomain)
        .toList();
  }

  @Override
  public Volume linkVolume(Instance instance, VolumeId volumeId) {
    AttachVolumeRequestBody body = new AttachVolumeRequestBody();
    body.setLinodeId(instance.getId().id());
    body.setPersistAcrossBoots(false);

    LinodeVolume volume = linodeInterface.attach(volumeId.id(), body);

    return volume.toDomain();
  }
}
