package com.bjoggis.linode4j.api.model.type;

import java.util.List;

public record Backups(Price price, List<RegionPrice> region_prices) {

}
