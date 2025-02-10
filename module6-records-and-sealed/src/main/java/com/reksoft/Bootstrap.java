package com.reksoft;

import com.reksoft.model.Circle;
import com.reksoft.model.Figure;
import com.reksoft.model.IsoscelesTriangle;
import com.reksoft.model.Rectangle;
import com.reksoft.model.Square;

import com.reksoft.model.Triangle;

import java.math.BigDecimal;

public class Bootstrap {
  public static void main(String[] args) {

    //Circle
    Figure circle = new Circle(BigDecimal.TEN);
    System.out.println("circle area = " + circle.getArea());

    //Square
    Figure square = new Square(BigDecimal.TEN);
    System.out.println("square area = " + square.getArea());

    //Rectangle
    Figure rectangle = new Rectangle(BigDecimal.TEN, BigDecimal.valueOf(25L));
    System.out.println("rectangle area = " + rectangle.getArea());

    //Triangle
    Figure triangle = new Triangle(BigDecimal.TEN, BigDecimal.valueOf(15L));
    System.out.println("triangle area = " + triangle.getArea());

    //IsoscelesTriangle
    Figure isoscelesTriangle = new IsoscelesTriangle(BigDecimal.TEN, BigDecimal.valueOf(15L));
    System.out.println("isoscelesTriangle area = " + isoscelesTriangle.getArea());

  }
}