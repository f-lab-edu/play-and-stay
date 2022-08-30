package com.world.playstay.auth.dto.request;

import com.world.playstay.auth.enums.LoginMethod;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogoutRequest {

  @NotNull
  private final LoginMethod loginMethod;

}
