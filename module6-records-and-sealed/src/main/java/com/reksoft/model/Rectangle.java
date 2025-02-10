package com.reksoft.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class Rectangle extends Square {

  private BigDecimal secondSide;

  public Rectangle(BigDecimal side, BigDecimal secondSide) {
    super(side); // Теперь правильно вызываем конструктор родительского класса
    this.secondSide = secondSide;
  }

  @Override
  public BigDecimal getArea() {
    return super.getSide().multiply(secondSide);
  }
}
