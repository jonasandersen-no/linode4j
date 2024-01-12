package com.bjoggis.linode4j.domain;

public class Volume {

  private VolumeId id;
  private String label;
  private String status;
  private LinodeId linodeId;

  private Tag tag;

  public Volume() {
  }

  public Volume(VolumeId id) {
    this.id = id;
  }

  public Volume(VolumeId id, String label, String status, LinodeId linodeId) {
    this.id = id;
    this.label = label;
    this.status = status;
    this.linodeId = linodeId;
  }

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

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "Volume{" +
        "id=" + id +
        ", label='" + label + '\'' +
        ", status='" + status + '\'' +
        ", linodeId=" + linodeId +
        '}';
  }
}
