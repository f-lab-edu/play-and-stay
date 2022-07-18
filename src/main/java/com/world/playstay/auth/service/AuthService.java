package com.world.playstay.auth.service;

import com.world.playstay.auth.dto.request.LoginRequest;
import com.world.playstay.global.util.HashUtil;
import com.world.playstay.user.enums.UserType;
import com.world.playstay.user.service.GuestService;
import com.world.playstay.user.service.HostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final GuestService guestService;
  private final HostService hostService;

  public Long validateUser(LoginRequest loginRequest) {
    Long userId;
    String encryptedPassword = HashUtil.encryptSHA256(loginRequest.getPassword());
    if (loginRequest.getType().equals(UserType.GUEST)) {
      userId = guestService.validateLoginInfoOrElseThrow(loginRequest.getEmail(),
          encryptedPassword);
    } else {
      userId = hostService.validateLoginInfoOrElseThrow(loginRequest.getEmail(), encryptedPassword);
    }
    return userId;

  }

}
