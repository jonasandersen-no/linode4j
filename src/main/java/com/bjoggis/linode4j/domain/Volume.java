package com.bjoggis.linode4j.domain;

public class Volume {

  private VolumeId id;
  private String label;
  private String status;
  private LinodeId linodeId;

  public VolumeId getId() {
    return id;
  }

  public void setId(VolumeId id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LinodeId getLinodeId() {
    return linodeId;
  }

  public void setLinodeId(LinodeId linodeId) {
    this.linodeId = linodeId;
  }
}
