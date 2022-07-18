package com.world.playstay.auth.service;

import com.world.playstay.auth.constant.SessionConstant;
import com.world.playstay.auth.dto.request.LoginRequest;
import com.world.playstay.auth.exception.InvalidHttpSessionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SessionLoginService implements LoginService {

  private static final String SESSION_LOG_FORMAT = "[HttpSession] method: {} | type: {} | email: {} | message: {}";

  public boolean setLoginStatus(HttpServletRequest httpServletRequest, LoginRequest loginRequest) {
    HttpSession httpSession = httpServletRequest.getSession(true);

    httpSession.setAttribute(SessionConstant.SESSION_ID, loginRequest.getEmail());
    httpSession.setAttribute(SessionConstant.SESSION_USER_TYPE, loginRequest.getType());

    log.info(SESSION_LOG_FORMAT, "set", loginRequest.getType(), loginRequest.getEmail(),
        "Login Successfully");
    return true;
  }

  public boolean validateLoginStatus(HttpServletRequest httpServletRequest) {
    HttpSession httpSession = httpServletRequest.getSession(false);
    try {
      return httpSession.getAttribute(SessionConstant.SESSION_ID) != null
          && httpSession.getAttribute(SessionConstant.SESSION_USER_TYPE) != null;
    } catch (Exception e) {
      throw new InvalidHttpSessionException(e.getMessage());
    }
  }

  public void invalidateLoginStatus(HttpServletRequest httpServletRequest) {
    HttpSession httpSession = httpServletRequest.getSession(false);
    if (httpSession != null) {
      httpSession.invalidate();
      log.info(SESSION_LOG_FORMAT, "invalidate",
          httpSession.getAttribute(SessionConstant.SESSION_USER_TYPE),
          httpSession.getAttribute(SessionConstant.SESSION_ID), "Logout Successfully");
    } else {
      throw new InvalidHttpSessionException("Invalid session");
    }
  }


}
