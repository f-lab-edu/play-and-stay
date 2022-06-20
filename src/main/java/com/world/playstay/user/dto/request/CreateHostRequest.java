package com.world.playstay.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateHostRequest {

  @NotNull
  private final String firstName;

  @NotNull
  private final String lastName;

  private final String nickName;

  @NotNull
  private final String phone;

  @NotNull
  @Email()
  private final String email;

  @NotNull
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[&!@#$%^&*])[A-Za-z\\d&!@#$%^&*]{10,20}", message = "Password contains 10-20 characters including at least one uppercase, lowercase, and special characters (&!@#$%^&*)")
  private final String password;

}
