package com.world.playstay.auth.service;

import com.world.playstay.auth.dto.request.LoginRequest;
import javax.servlet.http.HttpServletRequest;

public interface LoginService {

  boolean setLoginStatus(HttpServletRequest httpServletRequest, LoginRequest loginRequest);

  boolean validateLoginStatus(HttpServletRequest httpServletRequest);

  void invalidateLoginStatus(HttpServletRequest httpServletRequest);

}
