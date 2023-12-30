package com.bjoggis.linode4j.adapter.out.api.model;

import com.bjoggis.linode4j.adapter.out.api.model.region.Resolvers;
import java.util.List;

public record Region(String id, String label, String country, List<String> capabilities,
                     String status, Resolvers resolvers) {

}

