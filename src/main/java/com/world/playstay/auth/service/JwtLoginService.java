package com.world.playstay.auth.service;

import com.world.playstay.user.enums.UserType;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class JwtLoginService implements LoginService {

  @Override
  public void setLoginStatus(HttpServletRequest httpServletRequest,
      String email,
      UserType userType) {
  }

  @Override
  public void validateLoginStatus(HttpServletRequest httpServletRequest) {
  }

  @Override
  public void invalidateLoginStatus(HttpServletRequest httpServletRequest) {
  }
}
