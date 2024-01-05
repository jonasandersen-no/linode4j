package com.bjoggis.linode4j.domain;

public class InstanceNotFoundException extends Exception {

  public InstanceNotFoundException(LinodeId id) {
    super("Instance with id " + id + " not found");
  }

}
