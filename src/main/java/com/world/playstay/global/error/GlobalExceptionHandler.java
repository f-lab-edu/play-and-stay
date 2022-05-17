package com.world.playstay.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 핸들러
 * 모든 RuntimeException을 공통 response format인 GlobalExceptionResponse로 처리한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(GlobalHttpException.class)
  public ResponseEntity<GlobalExceptionResponse> handleGlobalHttpException(GlobalHttpException e){
    GlobalExceptionResponse response = new GlobalExceptionResponse(e.getStatus(), e.getClass().getSimpleName(), e.getMessage());
    return ResponseEntity.status(e.getStatus()).body(response);
  }
}
