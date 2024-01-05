package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import java.util.List;

public interface LinodeApi {

  Instance createInstance();

  List<Instance> listInstances();

  void delete(LinodeId id);

  List<Volume> findVolumes();
  Volume linkVolume(Instance instance, VolumeId volumeId);
}
