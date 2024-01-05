package com.bjoggis.linode4j.domain;

public class VolumeNotFoundException extends RuntimeException {

  public VolumeNotFoundException(VolumeId id) {
    super("LinodeVolume with id " + id + " not found");
  }

}
