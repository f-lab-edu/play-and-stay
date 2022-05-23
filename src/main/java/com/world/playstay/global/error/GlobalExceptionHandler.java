package com.world.playstay.global.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 핸들러
 * 모든 RuntimeException을 공통 response format인 GlobalExceptionResponse로 처리한다.
 * 프로젝트에서 정의하지 않은 Exception이 발생하면 INTERNAL SERVER ERROR로 처리한다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(GlobalHttpException.class)
  public ResponseEntity<GlobalExceptionResponse> handleGlobalHttpException(GlobalHttpException e){
    GlobalExceptionResponse response = new GlobalExceptionResponse(e.getStatus(), e.getClass().getSimpleName(), e.getMessage());
    e.createLog(logger, e.getClass().getSimpleName());
    return ResponseEntity.status(e.getStatus()).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GlobalExceptionResponse> handleException(Exception e){
    GlobalExceptionResponse response = new GlobalExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getClass().getSimpleName(), e.getMessage());
    logger.error("[EXCEPTION] errorCode: {} | message: {}", e.getClass().getSimpleName(), e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
