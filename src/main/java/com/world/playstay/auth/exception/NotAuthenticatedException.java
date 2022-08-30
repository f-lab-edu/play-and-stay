package com.world.playstay.auth.exception;

import com.world.playstay.global.exception.GlobalHttpException;
import com.world.playstay.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class NotAuthenticatedException extends GlobalHttpException {

  private static final String MESSAGE = "Not authenticated user";

  public NotAuthenticatedException() {
    super(HttpStatus.UNAUTHORIZED, MESSAGE, LogLevel.ERROR);
  }

  public NotAuthenticatedException(String message) {
    super(HttpStatus.UNAUTHORIZED, message, LogLevel.ERROR);
  }

}
