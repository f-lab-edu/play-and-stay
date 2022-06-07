package com.world.playstay.user.dao;

import com.world.playstay.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
  void insert(User user);
  void delete(Long id);
  Optional<User> findById(Long id);
  List<User> findAll();
}
