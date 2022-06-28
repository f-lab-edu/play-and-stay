package com.world.playstay.user.exception;

import com.world.playstay.global.exception.GlobalHttpException;
import com.world.playstay.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalHttpException {

  public UserNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message, LogLevel.ERROR);
  }
}
