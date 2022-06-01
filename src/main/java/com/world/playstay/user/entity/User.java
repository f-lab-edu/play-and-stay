package com.world.playstay.user.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {

  private Long id;
  private String name;
  private String email;
  private Date createdAt;

}

