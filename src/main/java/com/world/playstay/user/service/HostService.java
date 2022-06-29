package com.world.playstay.user.service;

import com.world.playstay.global.util.HashUtil;
import com.world.playstay.user.dao.HostMapper;
import com.world.playstay.user.entity.Host;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HostService {

  private final HostMapper hostMapper;

  public void join(Host host, String password) {
    getByEmailOrElseNull(host.getEmail()).ifPresent(Host -> {
      throw new DuplicatedUserException("Host already exists with this email");
    });
    host.setEncryptedPassword(HashUtil.encryptSHA256(password));
    hostMapper.insert(host);
  }

  public void remove(Long id) {
    getByIdOrElseThrow(id);
    hostMapper.delete(id);
  }

  public Host getByIdOrElseThrow(Long id) {
    return hostMapper.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Host does not exist"));
  }


  public Optional<Host> getByIdOrElseNull(Long id) {
    return hostMapper.findById(id);
  }


  public Optional<Host> getByEmailOrElseNull(String email) {
    return hostMapper.findByEmail(email);
  }


  public List<Host> getAll() {
    return hostMapper.findAll();
  }

}
