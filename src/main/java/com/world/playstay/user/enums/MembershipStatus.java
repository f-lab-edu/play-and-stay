package com.world.playstay.user.enums;

import com.world.playstay.global.enums.GlobalEnum;
import com.world.playstay.global.enums.GlobalEnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;

public enum MembershipStatus implements GlobalEnum {
  BASIC("BASIC"),
  STANDARD("STANDARD"),
  PREMIUM("PREMIUM");

  private final String code;

  MembershipStatus(String code) {
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }

  @MappedTypes(MembershipStatus.class)
  public static class TypeHandler extends GlobalEnumTypeHandler<MembershipStatus> {

    public TypeHandler() {
      super(MembershipStatus.class);
    }
  }
}
