package com.world.playstay.user.service;

import com.world.playstay.user.dao.GuestMapper;
import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestService implements UserService<Guest> {

  private final GuestMapper guestMapper;

  public void join(Guest guest) {
    getByIdOrElseNull(guest.getId()).ifPresent(Guest -> {
      throw new DuplicatedUserException("Guest already exists");
    });
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

  public Optional<Guest> getByIdOrElseNull(Long id) {
    return guestMapper.findById(id);
  }


}
