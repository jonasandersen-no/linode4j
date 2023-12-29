package com.bjoggis.linode4j;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "linode")
@Validated
public record LinodeProperties(@NotNull String baseUrl, @NotNull String token) {

}
