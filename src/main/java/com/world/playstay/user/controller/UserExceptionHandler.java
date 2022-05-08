package com.world.playstay.user.controller;

import com.world.playstay.global.GlobalExceptionResponse;
import com.world.playstay.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public GlobalExceptionResponse handleUserNotFoundException(UserNotFoundException e) {
    return new GlobalExceptionResponse(404, e.getMessage());
  }

}
