package com.world.playstay.auth.dto.response;

import com.world.playstay.user.enums.UserType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

  private Long id;
  private UserType type;
  private String email;
  private LocalDateTime loginAt;

}
