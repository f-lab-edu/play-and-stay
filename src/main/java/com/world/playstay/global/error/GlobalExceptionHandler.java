package com.world.playstay.global.error;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 전역 예외 처리 핸들러
 모든 RuntimeException을 공통 response format인 GlobalExceptionResponse로 처리한다.
 프로젝트에서 정의하지 않은 Exception이 발생하면 INTERNAL SERVER ERROR로 처리한다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final String ERROR_MESSAGE_FORMAT = "[EXCEPTION] path: {} | errorCode: {} | message: {}";

  public void createLog(LogLevel logLevel, Exception e, String requestPath) {
    if (logLevel == LogLevel.NONE) {
      return;
    }
    if (logLevel == LogLevel.ERROR) {
      log.error(ERROR_MESSAGE_FORMAT, requestPath,
          e.getClass().getSimpleName(),
          e.getMessage(), e);
    } else {
      log.warn(ERROR_MESSAGE_FORMAT, requestPath,
          e.getClass().getSimpleName(),
          e.getMessage(), e);
    }
  }

  @ExceptionHandler(GlobalHttpException.class)
  public ResponseEntity<GlobalExceptionResponse> handleGlobalHttpException(GlobalHttpException e,
      HttpServletRequest request) {
    GlobalExceptionResponse response = new GlobalExceptionResponse(e.getStatus(),
        e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
    createLog(e.getLogLevel(), e, request.getRequestURI());
    return ResponseEntity.status(e.getStatus()).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GlobalExceptionResponse> handleException(Exception e,
      HttpServletRequest request) {
    GlobalExceptionResponse response = new GlobalExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
    createLog(LogLevel.ERROR, e, request.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
