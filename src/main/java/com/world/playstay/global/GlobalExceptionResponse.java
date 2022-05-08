package com.world.playstay.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalExceptionResponse {
  private int statusCode;
  private String message;
}
