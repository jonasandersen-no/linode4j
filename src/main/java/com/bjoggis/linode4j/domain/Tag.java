package com.bjoggis.linode4j.domain;

public record Tag(String value) {

  public static Tag of(String value) {
    return new Tag(value);
  }
}
