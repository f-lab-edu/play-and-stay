package com.world.playstay.global.util.exception;

import com.world.playstay.global.exception.GlobalHttpException;
import com.world.playstay.global.exception.LogLevel;
import org.springframework.http.HttpStatus;

public class EncryptException extends GlobalHttpException {

  public EncryptException(String message) {
    super(HttpStatus.BAD_REQUEST, message, LogLevel.ERROR);
  }
}

