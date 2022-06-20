package com.world.playstay.user.entity;

public abstract class User {

  public enum AuthStatus {
    UNAUTHENTICATED,
    AUTHENTICATED,
  }

  public enum UserType {
    HOST,
    GUEST,
  }

}

