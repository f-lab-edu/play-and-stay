package com.world.playstay.user.dao;

import com.world.playstay.user.entity.Host;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HostMapper {

  void insert(Host user);

  void delete(Long id);

  Optional<Host> findById(Long id);

  Optional<Host> findByEmail(String email);

  List<Host> findAll();
}
