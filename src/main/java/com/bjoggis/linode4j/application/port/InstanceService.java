package com.bjoggis.linode4j.application.port;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.InstanceNotFoundException;
import com.bjoggis.linode4j.domain.LinodeId;
import com.bjoggis.linode4j.domain.Volume;
import com.bjoggis.linode4j.domain.VolumeId;
import com.bjoggis.linode4j.domain.VolumeNotFoundException;
import java.util.List;

public interface InstanceService {

  Instance createInstance();

  Instance findInstance(LinodeId id) throws InstanceNotFoundException;

  List<Instance> listInstances();

  void deleteInstance(LinodeId id);

  /**
   * Links a volume to an instance. Throws an Exception if the instance or volume does not exist.
   *
   * @param id       The instance id
   * @param volumeId The volume id
   */
  void linkVolume(LinodeId id, VolumeId volumeId) throws InstanceNotFoundException;

  Volume findVolume(VolumeId volumeId) throws VolumeNotFoundException;
}
