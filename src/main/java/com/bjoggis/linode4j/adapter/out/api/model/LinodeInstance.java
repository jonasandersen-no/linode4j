package com.bjoggis.linode4j.adapter.out.api.model;

import com.bjoggis.linode4j.adapter.out.api.model.instance.Alerts;
import com.bjoggis.linode4j.adapter.out.api.model.instance.Backups;
import com.bjoggis.linode4j.adapter.out.api.model.instance.Specs;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.time.LocalDateTime;
import java.util.List;

public record LinodeInstance(
    long id,
    String label,
    String group,
    String status,
    LocalDateTime created,
    LocalDateTime updated,
    String type,
    List<String> ipv4,
    String ipv6,
    String image,
    String region,
    Specs specs,
    Alerts alerts,
    Backups backups,
    String hypervisor,
    boolean watchdogEnabled,
    List<String> tags,
    String hostUuid,
    boolean hasUserData
) {

  public LinodeInstance {
    if (id <= 0) {
      throw new IllegalArgumentException("Id must be positive");
    }
    if (ipv4 == null || ipv4.isEmpty()) {
      throw new IllegalArgumentException("At least one IPv4 address is required");
    }
    if (ipv6 == null || ipv6.isEmpty()) {
      throw new IllegalArgumentException("IPv6 address is required");
    }
  }

  public Instance toDomain() {
    final Instance instance = new Instance();

    instance.setId(LinodeId.of(id));
    instance.setLabel(label);
    instance.setIp(ipv4.getFirst());
    instance.setStatus(status);
    instance.setCreated(created);

    return instance;
  }
}
