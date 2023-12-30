package com.bjoggis.linode4j.adapter.out.api.model;

import java.util.List;

public record Page<T>(List<T> data, Integer page, Integer pages, Integer results) {

}
