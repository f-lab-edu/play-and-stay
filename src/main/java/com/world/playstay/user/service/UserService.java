package com.world.playstay.user.service;

import com.world.playstay.user.dto.UserDto;
import com.world.playstay.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public void join(UserDto userDto) {
    userMapper.insert(userDto);
  }

}
