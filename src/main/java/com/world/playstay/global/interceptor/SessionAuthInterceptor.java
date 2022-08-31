package com.world.playstay.global.interceptor;

import com.world.playstay.auth.service.SessionLoginService;
import com.world.playstay.global.annotation.SessionAuthAnnotation;
import com.world.playstay.user.enums.UserType;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @SessionAuthAnnotation 어노테이션이 붙은 handler 로그인 상태 유효성 검증
 */
@Component
@RequiredArgsConstructor
public class SessionAuthInterceptor implements HandlerInterceptor {

  private final SessionLoginService loginService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws Exception {
    boolean isAuth = true;

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    SessionAuthAnnotation authAnnotation = handlerMethod.getMethod()
        .getAnnotation(SessionAuthAnnotation.class);

    // method에 @SessionAuthAnnotation이 없어서 인증이 불필요한 경우 유효성 검증 생략
    if (authAnnotation == null) {
      return isAuth;
    }

    UserType[] authTypes = authAnnotation.userType();
    if (Arrays.asList(authTypes).contains(UserType.HOST) && Arrays.asList(authTypes)
        .contains(UserType.GUEST)) { // Guest, Host 구분이 필요없는 유효성 검증
      loginService.validateLoginStatus(request);
    } else if (authTypes[0] == UserType.GUEST) {
      loginService.validateGuestLoginStatus(request);
    } else if (authTypes[0] == UserType.HOST) {
      loginService.validateHostLoginStatus(request);
    }

    return isAuth;
  }

}
