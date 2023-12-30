package com.bjoggis.linode4j.adapter.out.api.model.type;

import java.util.List;

public record Backups(Price price, List<RegionPrice> region_prices) {

}
