package com.world.playstay.user.enums;

import com.world.playstay.global.enums.GlobalEnum;
import com.world.playstay.global.enums.GlobalEnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;

public enum AuthStatus implements GlobalEnum {
  UNAUTHENTICATED("UNAUTHENTICATED"),
  AUTHENTICATED("AUTHENTICATED");

  private final String code;

  AuthStatus(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }

  @MappedTypes(AuthStatus.class)
  public static class TypeHandler extends GlobalEnumTypeHandler<AuthStatus> {

    public TypeHandler() {
      super(AuthStatus.class);
    }
  }

}