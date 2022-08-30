package com.world.playstay.auth.service;

import com.world.playstay.auth.dto.request.LoginRequest;
import com.world.playstay.auth.enums.LoginMethod;
import com.world.playstay.auth.factory.LoginServiceFactory;
import com.world.playstay.global.util.HashUtil;
import com.world.playstay.user.entity.User;
import com.world.playstay.user.enums.UserType;
import com.world.playstay.user.service.GuestService;
import com.world.playstay.user.service.HostService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final LoginServiceFactory loginServiceFactory;
  private final GuestService guestService;
  private final HostService hostService;

  private LoginService getLoginService(LoginMethod loginMethod) {
    return loginServiceFactory.getInstance(loginMethod);
  }

  public User login(HttpServletRequest httpServletRequest, LoginRequest loginRequest,
      UserType userType) {
    // 유효한 이메일, 비밀번호 정보인지 확인
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();
    User user = validateUser(email, password, userType);

    // LoginMethod 정보로 bean 추출
    LoginService loginService = getLoginService(loginRequest.getLoginMethod());

    // 로그인 상태 설정
    loginService.setLoginStatus(httpServletRequest, email, userType);

    return user;
  }

  public void logout(HttpServletRequest httpServletRequest, LoginMethod loginMethod) {
    LoginService loginService = getLoginService(loginMethod);
    loginService.invalidateLoginStatus(httpServletRequest);
  }

  public User validateUser(String email, String password, UserType userType) {
    // 비밀번호 암호화
    String encryptedPassword = HashUtil.encryptSHA256(password);

    return userType == UserType.GUEST ?
        guestService.validateLoginInfoOrElseThrow(email, encryptedPassword)
        : hostService.validateLoginInfoOrElseThrow(email, encryptedPassword);
  }

}
