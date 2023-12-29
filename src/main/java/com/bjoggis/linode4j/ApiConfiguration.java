package com.bjoggis.linode4j;

import com.bjoggis.linode4j.api.LinodeInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ApiConfiguration {

  @Bean
  LinodeInterface linodeInterface(LinodeProperties properties) {
    RestClient restClient = RestClient.builder()
        .baseUrl(properties.baseUrl())
        .requestInitializer(request -> request.getHeaders().setBearerAuth(properties.token()))
        .build();

    RestClientAdapter adapter = RestClientAdapter.create(restClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

    return factory.createClient(LinodeInterface.class);
  }
}
