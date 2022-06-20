package com.world.playstay.user.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HostResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String nickName;
  private String email;
  private Integer authStatus;
  private Integer membershipStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
