package com.bjoggis.linode4j.web;

import java.time.LocalDateTime;

public record ListInstanceResponse(long id, String label, String ip, String status, LocalDateTime created) {

}
