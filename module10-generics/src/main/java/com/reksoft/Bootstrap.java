package com.reksoft;

import java.util.Objects;

public class Bootstrap {
  public static void main(String[] args) {
    System.out.println("///////////////////////////////////////");
    System.out.println("map()");

    ArrayListDecorator<Integer> integerListBeforeMap = new ArrayListDecorator<>(1, 2, 3, 4);
    ArrayListDecorator<Integer> integerListAfterMap = integerListBeforeMap.map(x -> x * 2);
    System.out.println("\nInteger list before map(x -> x * 2): " + integerListBeforeMap);
    System.out.println("Integer list after map(x -> x * 2): " + integerListAfterMap);

    ArrayListDecorator<String> stringListAfterMap = integerListBeforeMap.map(Objects::toString);
    System.out.println("\nInteger list after map(x -> x.toString()): " + stringListAfterMap
        + "\nElements has Class: " + stringListAfterMap.list.getFirst().getClass());

    System.out.println("\n///////////////////////////////////////");
    ArrayListDecorator<Integer> integerListBeforeReduce = new ArrayListDecorator<>(1, 2, 3, 4);
    System.out.println("Integer list before reduce((x, y) -> x + y): " + integerListBeforeReduce);
    Integer reduceResult = integerListBeforeReduce.reduce(Integer::sum);
    System.out.println("Result reduce((x, y) -> x + y): " + reduceResult);
  }
}