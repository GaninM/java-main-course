package com.reksoft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ArrayListDecorator<T> {

  List<T> list;

  public ArrayListDecorator() {
    list = new ArrayList<>();
  }

  public ArrayListDecorator(List<T> list) {
    this.list = new ArrayList<>(list);
  }

  @SafeVarargs
  public ArrayListDecorator(T... elements) {
    this.list = new ArrayList<>();
    Collections.addAll(this.list, elements);
  }

  public <R> ArrayListDecorator<R> map(Function<T, R> mapper) {
    List<R> mappedList = new ArrayList<>();
    for (T element : this.list) {
      mappedList.add(mapper.apply(element));
    }
    return new ArrayListDecorator<>(mappedList);
  }

  public T reduce(BiFunction<T, T, T> function) {
    if(list.isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    T result = list.getFirst();
    for (int i = 1; i < list.size(); i++) {
      result = function.apply(result, list.get(i));
    }
    return result;
  }

  @Override
  public String toString() {
    return list.toString();
  }
}
