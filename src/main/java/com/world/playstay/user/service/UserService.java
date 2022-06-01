package com.world.playstay.user.service;

import com.world.playstay.user.dto.UserDto;
import com.world.playstay.user.exception.UserNotFoundException;
import com.world.playstay.user.dao.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public void join(UserDto userDto) {
    userMapper.insert(userDto);
  }

  public UserDto findUser(Long id) {
    Optional<UserDto> user = Optional.ofNullable(userMapper.findById(id));
    return user.orElseThrow(() -> new UserNotFoundException("User does not exist"));
  }

}
