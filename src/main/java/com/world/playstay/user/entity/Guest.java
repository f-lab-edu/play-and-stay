package com.world.playstay.user.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Guest {

  private Long id;
  private String firstName;
  private String lastName;
  private String nickName;
  private String phone;
  private String email;
  private String encryptedPassword;
  private Integer authStatus;
  private LocalDate birth;
  private Integer countryCode;
  private Integer countMonthlyStamp;
  private Integer countCoupon;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
