package com.bjoggis.linode4j.adapter.out.db;

import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.convert.Jsr310Converters.DateToLocalDateTimeConverter;

@Entity
@Table(name = "linode_instance")
class LinodeInstanceDbo {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "linode_id", nullable = false)
  private Long linodeId;

  @Column(name = "label", nullable = false)
  private String label;

  @Column(name = "ip", nullable = false)
  private String ip;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "created")
  private Date created = new Date();

  static LinodeInstanceDbo fromInstance(Instance linodeInstance) {
    LinodeInstanceDbo dbo = new LinodeInstanceDbo();
    dbo.setLinodeId(linodeInstance.getId().id());
    dbo.setLabel(linodeInstance.getLabel());
    dbo.setIp(linodeInstance.getIp());
    dbo.setStatus(linodeInstance.getStatus());
    return dbo;
  }

  Optional<Instance> toInstanceOptional() {
    return Optional.of(toInstance());
  }

  Instance toInstance() {
    DateToLocalDateTimeConverter converter = DateToLocalDateTimeConverter.INSTANCE;
    LocalDateTime date = converter.convert(created);
    return new Instance(LinodeId.of(linodeId), label, ip, status, date);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getLinodeId() {
    return linodeId;
  }

  public void setLinodeId(Long linodeId) {
    this.linodeId = linodeId;
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

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
