package com.world.playstay.global.exception;

import org.springframework.http.HttpStatus;

public class EncryptFailException extends GlobalHttpException {

  public EncryptFailException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message, LogLevel.ERROR);
  }

}
