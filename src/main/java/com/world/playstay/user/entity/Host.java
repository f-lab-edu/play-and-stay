package com.world.playstay.user.entity;

import com.world.playstay.user.enums.AuthStatus;
import com.world.playstay.user.enums.MembershipStatus;
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

  private Long id;
  private String firstName;
  private String lastName;
  private String nickName;
  private String phone;
  private String email;
  private String encryptedPassword;
  private AuthStatus authStatus;
  private MembershipStatus membershipStatus;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
