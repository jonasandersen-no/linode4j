package com.bjoggis.linode4j.adapter.out.api.model;

import com.bjoggis.linode4j.adapter.out.api.model.type.Addons;
import com.bjoggis.linode4j.adapter.out.api.model.type.Price;
import com.bjoggis.linode4j.adapter.out.api.model.type.RegionPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record InstanceType(String id, String label, Price price,
                           @JsonProperty("region_prices") List<RegionPrice> regionPrices,
                           Addons addons, int memory, int disk, int transfer, int vcpus, int gpus,
                           @JsonProperty("network_out") int networkOut,
                           @JsonProperty("class") String clazz, String successor) {

}

