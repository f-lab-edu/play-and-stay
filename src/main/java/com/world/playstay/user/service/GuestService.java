package com.world.playstay.user.service;

import com.world.playstay.global.util.HashUtil;
import com.world.playstay.user.dao.GuestMapper;
import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestService implements UserService<Guest> {

  private final GuestMapper guestMapper;

  @Override
  public void join(Guest guest, String password) {
    getByEmailOrElseNull(guest.getEmail()).ifPresent(Guest -> {
      throw new DuplicatedUserException("Guest already exists with this email");
    });
    guest.setEncryptedPassword(HashUtil.encryptSHA256(password));
    guestMapper.insert(guest);
  }

  @Override
  public void remove(Long id) {
    getByIdOrElseThrow(id);
    guestMapper.delete(id);
  }

  @Override
  public Guest getByIdOrElseThrow(Long id) {
    return guestMapper.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Guest does not exist"));
  }

  @Override
  public Optional<Guest> getByIdOrElseNull(Long id) {
    return guestMapper.findById(id);
  }

  @Override
  public Optional<Guest> getByEmailOrElseNull(String email) {
    return guestMapper.findByEmail(email);
  }

  @Override
  public List<Guest> getAll() {
    return guestMapper.findAll();
  }


}
