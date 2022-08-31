package com.world.playstay.auth.dto.response;

import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.entity.Host;
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

  public static LoginResponse from(Guest guest, LocalDateTime date) {
    return LoginResponse.builder()
        .id(guest.getId())
        .email(guest.getEmail())
        .type(UserType.GUEST)
        .loginAt(date)
        .build();
  }

  public static LoginResponse from(Host host, LocalDateTime date) {
    return LoginResponse.builder()
        .id(host.getId())
        .email(host.getEmail())
        .type(UserType.HOST)
        .loginAt(date)
        .build();
  }

}
