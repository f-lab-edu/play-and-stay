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
    findUserById(user.getId()).ifPresent(User -> {
      throw new DuplicatedUserException("User already exists");
    });
    userMapper.insert(user);
  }

  public void remove(Long id) {
    getUser(id);
    userMapper.delete(id);
  }

  public Optional<User> findUserById(Long id) {
    return userMapper.findById(id);
  }


  public User getUser(Long id) {
    return findUserById(id).orElseThrow(() -> new UserNotFoundException("User does not exist"));
  }


  public List<User> getUsers() {
    return userMapper.findAll();
  }

}
