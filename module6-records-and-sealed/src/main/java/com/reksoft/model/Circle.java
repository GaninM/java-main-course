package com.reksoft.model;

import java.math.BigDecimal;

public record Circle(BigDecimal radius) implements Figure {

  @Override
  public BigDecimal getArea() {
    return radius
        .multiply(radius)
        .multiply(BigDecimal.valueOf(Math.PI));
  }
}
