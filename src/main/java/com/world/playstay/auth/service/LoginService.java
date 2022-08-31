package com.world.playstay.auth.service;

import com.world.playstay.user.enums.UserType;
import javax.servlet.http.HttpServletRequest;

public interface LoginService {

  void setLoginStatus(
      HttpServletRequest httpServletRequest,
      String email,
      UserType userType);

  void validateLoginStatus(HttpServletRequest httpServletRequest);

  void invalidateLoginStatus(HttpServletRequest httpServletRequest);

}
