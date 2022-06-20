package com.world.playstay.global.util.exception;

import com.world.playstay.global.error.GlobalHttpException;
import com.world.playstay.global.error.LogLevel;
import org.springframework.http.HttpStatus;

public class EncryptException extends GlobalHttpException {

  public EncryptException(String message) {
    super(HttpStatus.BAD_REQUEST, message, LogLevel.ERROR);
  }
}

