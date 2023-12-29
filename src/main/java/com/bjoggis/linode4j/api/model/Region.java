package com.bjoggis.linode4j.api.model;

import com.bjoggis.linode4j.api.model.region.Resolvers;
import java.util.List;

public record Region(String id, String label, String country, List<String> capabilities,
                     String status, Resolvers resolvers) {

}

