package com.bjoggis.linode4j.web;

import com.bjoggis.linode4j.domain.Instance;
import java.time.LocalDateTime;

public record ListInstanceResponse(long id, String label, String ip, String status,
                                   LocalDateTime created) {

  public static ListInstanceResponse fromDomain(Instance instance) {
    return new ListInstanceResponse(instance.getId().id(), instance.getLabel(),
        instance.getIp(), instance.getStatus(),
        instance.getCreated());
  }

}
