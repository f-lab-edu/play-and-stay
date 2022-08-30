package com.world.playstay.auth.enums;

import com.world.playstay.auth.service.JwtLoginService;
import com.world.playstay.auth.service.SessionLoginService;
import lombok.Getter;

@Getter
public enum LoginMethod {

  SESSION(SessionLoginService.class.getName()),
  JWT(JwtLoginService.class.getName());

  private final String beanName;

  LoginMethod(String beanName) {
    this.beanName = beanName;
  }

}
