package com.world.playstay.auth.service;

import com.world.playstay.auth.constant.SessionConstant;
import com.world.playstay.auth.exception.InvalidHttpSessionException;
import com.world.playstay.user.enums.UserType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SessionLoginService implements LoginService {

  private static final String SESSION_LOG_INFO_FORMAT = "[HttpSession] User Type = {} Email = {} {}";
  private static final String SESSION_LOG_ERROR_FORMAT = "[HttpSession] {}";

  public HttpSession getHttpSession(HttpServletRequest httpServletRequest, boolean create) {
    return httpServletRequest.getSession(create);
  }

  @Override
  public void setLoginStatus(HttpServletRequest httpServletRequest,
      String email,
      UserType userType) {
    HttpSession httpSession = getHttpSession(httpServletRequest, true);

    httpSession.setAttribute(SessionConstant.SESSION_ID, email);
    httpSession.setAttribute(SessionConstant.SESSION_USER_TYPE, userType);

    log.info(SESSION_LOG_INFO_FORMAT, userType,
        email,
        "Login Successfully");
  }

  @Override
  public void validateLoginStatus(HttpServletRequest httpServletRequest) {
    HttpSession httpSession = getHttpSession(httpServletRequest, false);
    validateHttpSession(httpSession);
  }

  public void validateHttpSession(HttpSession httpSession) {
    try {
      if (httpSession.getAttribute(SessionConstant.SESSION_ID) == null
          || httpSession.getAttribute(SessionConstant.SESSION_USER_TYPE) == null) {
        throw new InvalidHttpSessionException("Session keys not found");
      }
    } catch (Exception e) {
      log.error(SESSION_LOG_ERROR_FORMAT, e.getMessage());
      throw new InvalidHttpSessionException();
    }
  }

  public void validateHostLoginStatus(HttpServletRequest httpServletRequest) {
    HttpSession httpSession = getHttpSession(httpServletRequest, false);

    if (httpSession.getAttribute(SessionConstant.SESSION_USER_TYPE) != UserType.HOST) {
      throw new InvalidHttpSessionException("Invalid host session type");
    }
    validateHttpSession(httpSession);
  }

  public void validateGuestLoginStatus(HttpServletRequest httpServletRequest) {
    HttpSession httpSession = getHttpSession(httpServletRequest, false);

    if (httpSession.getAttribute(SessionConstant.SESSION_USER_TYPE) != UserType.GUEST) {
      throw new InvalidHttpSessionException("Invalid guest session type");
    }
    validateHttpSession(httpSession);
  }


  @Override
  public void invalidateLoginStatus(HttpServletRequest httpServletRequest) {
    HttpSession httpSession = getHttpSession(httpServletRequest, false);
    if (httpSession != null) {
      httpSession.invalidate();
      log.info(SESSION_LOG_INFO_FORMAT,
          httpSession.getAttribute(SessionConstant.SESSION_USER_TYPE),
          httpSession.getAttribute(SessionConstant.SESSION_ID), "Logout Successfully");
    } else {
      throw new InvalidHttpSessionException("Session not found");
    }
  }


}
