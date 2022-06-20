package com.world.playstay.user.service;

import java.util.List;
import java.util.Optional;

/**
 * Host 와 Guest의 공통 메소드를 정의합니다.
 */
public interface UserService<T> {

  public void join(T user, String password);

  public void remove(Long id);

  public T getByIdOrElseThrow(Long id);

  public Optional<T> getByIdOrElseNull(Long id);

  public Optional<T> getByEmailOrElseNull(String email);

  public List<T> getAll();

}
