package com.world.playstay.user.service;

import com.world.playstay.global.util.HashUtil;
import com.world.playstay.user.dao.HostMapper;
import com.world.playstay.user.entity.Host;
import com.world.playstay.user.enums.AuthStatus;
import com.world.playstay.user.enums.MembershipStatus;
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
    getByEmail(host.getEmail()).ifPresent(Host -> {
      throw new DuplicatedUserException("Host already exists with this email");
    });
    host.setEncryptedPassword(HashUtil.encryptSHA256(password));
    host.setAuthStatus(AuthStatus.UNAUTHENTICATED);
    host.setMembershipStatus(MembershipStatus.BASIC);
    hostMapper.insert(host);
  }


  public void remove(Long id) {
    getByIdOrElseThrow(id);
    hostMapper.delete(id);
  }

  public Long validateLoginInfoOrElseThrow(String email, String password) {
    Host host = hostMapper.findByEmailAndPassword(email, password)
        .orElseThrow(
            () -> new UserNotFoundException("No host exists in this email, password"));
    return host.getId();
  }

  public Host getByIdOrElseThrow(Long id) {
    return hostMapper.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Host does not exist"));
  }


  public Optional<Host> getById(Long id) {
    return hostMapper.findById(id);
  }


  public Optional<Host> getByEmail(String email) {
    return hostMapper.findByEmail(email);
  }


  public List<Host> getAll() {
    return hostMapper.findAll();
  }

}
