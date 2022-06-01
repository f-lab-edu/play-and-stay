package com.world.playstay.user.dao;

import com.world.playstay.user.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
  void insert(User user);
  void delete(String id);
  User findById(Long id);
}
