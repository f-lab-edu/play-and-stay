package com.world.playstay.user.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String name;
  private String email;
  private Date createdAt;
}
