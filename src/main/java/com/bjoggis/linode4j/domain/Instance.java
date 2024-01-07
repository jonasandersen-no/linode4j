package com.bjoggis.linode4j.domain;

import java.time.LocalDateTime;

public final class Instance {

  private LinodeId id;
  private String label;
  private String ip;
  private String status;
  private LocalDateTime created;

  public Instance() {
  }

  public Instance(LinodeId id) {
    this.id = id;
  }

  public Instance(LinodeId id, String label, String ip, String status, LocalDateTime date) {
    this.id = id;
    this.label = label;
    this.ip = ip;
    this.status = status;
    this.created = date;
  }

  public LinodeId getId() {
    return id;
  }

  public void setId(LinodeId id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @Override
  public String toString() {
    return "Instance{"
        + "id=" + id
        + ", label='" + label + '\''
        + ", ip='" + ip + '\''
        + ", status='" + status + '\''
        + ", created=" + created
        + '}';
  }
}
