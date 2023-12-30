package com.bjoggis.linode4j.adapter.out.api.model.instance;

import java.time.LocalDateTime;

public record Backups(boolean enabled, boolean available, Schedule schedule,
                      LocalDateTime lastSuccessful) {

}
