package com.reksoft.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public non-sealed class Triangle implements Figure {

  private BigDecimal base;
  private BigDecimal height;

  @Override
  public BigDecimal getArea() {
    return base
        .multiply(height)
        .divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
  }
}
