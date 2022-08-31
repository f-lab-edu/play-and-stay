package com.world.playstay.auth.factory;

import com.world.playstay.auth.enums.LoginMethod;
import com.world.playstay.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginServiceFactory {

  private final ApplicationContext applicationContext;

  public LoginService getInstance(LoginMethod loginMethod) {
    return (LoginService) applicationContext.getBean(loginMethod.getBeanName());
  }

}
