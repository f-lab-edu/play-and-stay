package com.world.playstay.auth.dto.request;

import com.world.playstay.user.enums.UserType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {

  @NotNull
  @Email()
  private final String email;

  @NotNull
  private final String password;

  @NotNull
  private final UserType type;

}
