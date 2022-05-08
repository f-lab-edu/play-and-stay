package com.world.playstay.user.mapper;

import com.world.playstay.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
  void insert(UserDto userDto);
  void delete(String id);
  UserDto findById(String id);
}
