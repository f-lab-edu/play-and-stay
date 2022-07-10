package com.world.playstay.user.dto.response;

import com.world.playstay.user.enums.AuthStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GuestResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String nickName;
  private String email;
  private AuthStatus authStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}

