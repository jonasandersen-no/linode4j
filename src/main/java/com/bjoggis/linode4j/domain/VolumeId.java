package com.bjoggis.linode4j.domain;

public record VolumeId(Long id) {

  public static VolumeId of(Long id) {
    return new VolumeId(id);
  }
}
