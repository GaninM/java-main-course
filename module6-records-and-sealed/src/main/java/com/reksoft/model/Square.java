package com.reksoft.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public sealed class Square implements Figure permits Rectangle {

  private BigDecimal side;

  @Override
  public BigDecimal getArea() {
    return side.multiply(side);
  }
}
