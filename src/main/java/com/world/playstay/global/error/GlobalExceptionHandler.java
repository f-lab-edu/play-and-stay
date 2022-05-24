package com.world.playstay.global.error;

import javax.servlet.http.HttpServletRequest;
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

  public void createLog(LogLevel logLevel, String errorCode, String message, String requestPath){
    if (logLevel == LogLevel.NONE){
      return;
    }
    if (logLevel == LogLevel.ERROR){
      logger.error("[EXCEPTION] path: {} | errorCode: {} | message: {}", requestPath, errorCode, message);
    }
    else{
      logger.warn("[EXCEPTION] path: {} | errorCode: {} | message: {}", requestPath, errorCode, message);
    }
  }

  @ExceptionHandler(GlobalHttpException.class)
  public ResponseEntity<GlobalExceptionResponse> handleGlobalHttpException(GlobalHttpException e,  HttpServletRequest request){
    GlobalExceptionResponse response = new GlobalExceptionResponse(e.getStatus(), e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
    createLog(e.getLogLevel(),  e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(e.getStatus()).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GlobalExceptionResponse> handleException(Exception e, HttpServletRequest request){
    GlobalExceptionResponse response = new GlobalExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
    createLog(LogLevel.ERROR,  e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
