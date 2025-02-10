package com.reksoft.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IsoscelesTriangle extends Triangle {

  public IsoscelesTriangle(BigDecimal base, BigDecimal height) {
    super(base, height);
  }
}
