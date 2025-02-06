package com.reksoft.module1rest.exception.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.reksoft.module1rest.Bootstrap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackageClasses = Bootstrap.class)
public class AdviceController {

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public String handleException(Exception e) {
    return e.getMessage();
  }
}
