package com.bjoggis.linode4j.domain;

public record LinodeId(Long id) {

  public static LinodeId of(Long id) {
    return new LinodeId(id);
  }
}
