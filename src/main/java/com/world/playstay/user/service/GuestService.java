package com.world.playstay.user.service;

import com.world.playstay.global.util.HashUtil;
import com.world.playstay.user.dao.GuestMapper;
import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.enums.AuthStatus;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestService {

  private final GuestMapper guestMapper;

  public void join(Guest guest, String password) {
    getByEmail(guest.getEmail()).ifPresent(Guest -> {
      throw new DuplicatedUserException("Guest already exists with this email");
    });
    guest.setEncryptedPassword(HashUtil.encryptSHA256(password));
    guest.setAuthStatus(AuthStatus.UNAUTHENTICATED.ordinal());
    guest.setCountMonthlyStamp(0);
    guest.setCountCoupon(0);
    guestMapper.insert(guest);
  }


  public void remove(Long id) {
    getByIdOrElseThrow(id);
    guestMapper.delete(id);
  }


  public Guest getByIdOrElseThrow(Long id) {
    return guestMapper.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Guest does not exist"));
  }

  public Optional<Guest> getById(Long id) {
    return guestMapper.findById(id);
  }

  public Optional<Guest> getByEmail(String email) {
    return guestMapper.findByEmail(email);
  }

  public List<Guest> getAll() {
    return guestMapper.findAll();
  }


}
