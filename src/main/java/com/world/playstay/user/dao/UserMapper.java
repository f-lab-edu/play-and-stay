package com.world.playstay.user.dao;

import java.util.List;
import java.util.Optional;


public interface UserMapper<T> {

  void insert(T user);

  void delete(Long id);

  Optional<T> findById(Long id);

  List<T> findAll();
}
