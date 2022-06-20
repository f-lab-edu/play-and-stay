package com.world.playstay.user.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Host extends User {

  public enum MemberShipStatus {
    BASIC,
    STANDARD,
    PREMIUM
  }

  private Long id;
  private String firstName;
  private String lastName;
  private String nickName;
  private String phone;
  private String email;
  private String encryptedPassword;
  private Integer authStatus;
  private Integer membershipStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
