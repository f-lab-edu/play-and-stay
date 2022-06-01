package com.world.playstay.user.service;

import com.world.playstay.user.entity.User;
import com.world.playstay.user.exception.UserNotFoundException;
import com.world.playstay.user.dao.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public void join(User user) {
    userMapper.insert(user);
  }

  public User findUser(Long id) {
    Optional<User> user = Optional.ofNullable(userMapper.findById(id));
    return user.orElseThrow(() -> new UserNotFoundException("User does not exist"));
  }

}
