package com.world.playstay.auth.dto.request;

import com.world.playstay.auth.dto.response.LoginResponse;
import com.world.playstay.user.enums.UserType;
import java.time.LocalDateTime;
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

  public LoginResponse toLoginResponse(Long id, LocalDateTime date) {
    return LoginResponse.builder()
        .id(id)
        .email(this.getEmail())
        .type(this.getType())
        .loginAt(date)
        .build();
  }

}
