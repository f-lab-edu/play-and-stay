package com.world.playstay.user.dao;


import com.world.playstay.user.entity.Guest;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestMapper {

  void insert(Guest guest);

  void delete(Long id);

  Optional<Guest> findById(Long id);

  Optional<Guest> findByEmail(String email);

  Optional<Guest> findByEmailAndPassword(String email, String encryptedPassword);

  List<Guest> findAll();

}

