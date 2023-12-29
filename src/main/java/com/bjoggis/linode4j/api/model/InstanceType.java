package com.bjoggis.linode4j.api.model;

import com.bjoggis.linode4j.api.model.type.Addons;
import com.bjoggis.linode4j.api.model.type.Price;
import com.bjoggis.linode4j.api.model.type.RegionPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record InstanceType(String id, String label, Price price, List<RegionPrice> region_prices,
                           Addons addons, int memory, int disk, int transfer, int vcpus, int gpus,
                           int network_out, @JsonProperty("class") String clazz, String successor) {

}

