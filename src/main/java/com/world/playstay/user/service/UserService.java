package com.world.playstay.user.service;

import com.world.playstay.user.entity.User;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import com.world.playstay.user.dao.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public void join(User user) {
    getByIdOrElseNull(user.getId()).ifPresent(User -> {
      throw new DuplicatedUserException("User already exists");
    });
    userMapper.insert(user);
  }

  public void remove(Long id) {
    getByIdOrElseThrow(id);
    userMapper.delete(id);
  }

  public User getByIdOrElseThrow(Long id) {
    return userMapper.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User does not exist"));
  }

  public Optional<User> getByIdOrElseNull(Long id) {
    return userMapper.findById(id);
  }

  public List<User> getUsers() {
    return userMapper.findAll();
  }

}
