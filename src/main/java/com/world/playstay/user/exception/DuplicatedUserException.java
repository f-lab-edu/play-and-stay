package com.world.playstay.user.exception;

import com.world.playstay.global.exception.GlobalHttpException;
import com.world.playstay.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class DuplicatedUserException extends GlobalHttpException {

  public DuplicatedUserException(String message) {
    super(HttpStatus.BAD_REQUEST, message, LogLevel.ERROR);
  }

}
