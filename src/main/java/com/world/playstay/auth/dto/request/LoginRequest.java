package com.world.playstay.auth.dto.request;

import com.world.playstay.auth.enums.LoginMethod;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {

  @NotNull
  @Email()
  private String email;

  @NotNull
  private String password;

  @NotNull
  private LoginMethod loginMethod;
}
