package com.reksoft.model;

import java.math.BigDecimal;

public sealed interface Figure permits Circle, Square, Triangle {

  BigDecimal getArea();
}
