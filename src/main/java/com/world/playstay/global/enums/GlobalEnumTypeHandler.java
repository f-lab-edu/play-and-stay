package com.world.playstay.global.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/*
TypeHandler의 역할:
MyBatis가 SELECT시에 PreparedStatement에 파라미터를 설정하고 ResultSet에서 값을 가져올때마다 적절한 Java 타입의 값을 가져오도록 해주고,
INSERT시에 PreparedStatement에 적절한 Java 타입의 값을 지정 해준다.

Enum class를 사용하면 Enum 상수 그대로 저장되는 이슈가 있다. 원하는 value로 저장하려면 Enum class마다 TypeHandler를 커스텀 해줘야 한다.
TypeHandler 인터페이스를 전역으로 구현하고, 자바 타입에 TypeHandler를 매핑해서 MyBatis TypeHandler configuration에 등록해서 사용한다.
 */
public class GlobalEnumTypeHandler<E extends Enum<E> & GlobalEnum> implements
    TypeHandler<GlobalEnum> {

  private Class<E> type;

  public GlobalEnumTypeHandler(Class<E> type) {
    this.type = type;
  }

  @Override
  public void setParameter(PreparedStatement ps, int i, GlobalEnum parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.getCode());
  }

  @Override
  public GlobalEnum getResult(ResultSet rs, String columnName) throws SQLException {
    return getEnumValue(rs.getString(columnName));
  }

  @Override
  public GlobalEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
    return getEnumValue(rs.getString(columnIndex));
  }

  @Override
  public GlobalEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return getEnumValue(cs.getString(columnIndex));
  }

  private GlobalEnum getEnumValue(String code) {
    return EnumSet.allOf(type)
        .stream()
        .filter(value -> value.getCode().equals(code))
        .findFirst()
        .orElseGet(null);
  }

}
