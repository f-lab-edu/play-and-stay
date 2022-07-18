package com.world.playstay.auth.exception;

import com.world.playstay.global.exception.GlobalHttpException;
import com.world.playstay.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class InvalidHttpSessionException extends GlobalHttpException {

  public InvalidHttpSessionException(String message) {
    super(HttpStatus.NOT_FOUND, message, LogLevel.ERROR);
  }

}
