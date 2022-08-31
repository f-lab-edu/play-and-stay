package com.world.playstay.auth.enums;

import com.world.playstay.auth.service.JwtLoginService;
import com.world.playstay.auth.service.SessionLoginService;
import lombok.Getter;

@Getter
public enum LoginMethod {

  SESSION(SessionLoginService.class.getSimpleName()),
  JWT(JwtLoginService.class.getSimpleName());

  private final String beanName;

  LoginMethod(String className) {
    this.beanName = makeBeanName(className);
  }

  private String makeBeanName(String className) {
    StringBuilder sb = new StringBuilder();
    String firstLetter = className.substring(0, 1).toLowerCase();
    return sb.append(firstLetter).append(className.substring(1)).toString();
  }

}
