package com.world.playstay.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.world.playstay.ServiceTest;
import com.world.playstay.user.dao.HostMapper;
import com.world.playstay.user.entity.Host;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("Host Service Test")
public class HostServiceTest extends ServiceTest {

  @InjectMocks
  private HostService hostService;

  @Mock
  private HostMapper hostMapper;

  private Host host;

  @BeforeEach
  public void createHost() {
    host = Host.builder()
        .id(1L)
        .firstName("테스트1")
        .lastName("테스트1")
        .nickName("테스트1")
        .phone("821011112222")
        .email("test1@gmail.com")
        .build();
  }

  @Test
  @DisplayName("id가 존재하는 host를 반환한다.")
  public void getByIdOrElseThrowSuccessTest() {
    when(hostMapper.findById(host.getId())).thenReturn(Optional.ofNullable(host));
    assertEquals(hostService.getByIdOrElseThrow(host.getId()), host);
    verify(hostMapper).findById(host.getId());
  }

  @Test
  @DisplayName("id가 존재하지 않을 시 에러가 발생한다.")
  public void getByIdOrElseThrowErrorTest() {
    when(hostMapper.findById(2L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> hostService.getByIdOrElseThrow(2L));
    verify(hostMapper).findById(2L);
  }

  @Test
  @DisplayName("id가 존재하는 host를 반환한다.")
  public void getByIdOrElseNullSuccessTest() {
    when(hostMapper.findById(host.getId())).thenReturn(Optional.ofNullable(host));
    assertEquals(hostService.getByIdOrElseNull(host.getId()), Optional.ofNullable(host));
    verify(hostMapper).findById(host.getId());
  }

  @Test
  @DisplayName("email이 존재하는 host를 반환한다.")
  public void getByEmailOrElseNullSuccessTest() {
    when(hostMapper.findByEmail(host.getEmail())).thenReturn(Optional.ofNullable(host));
    assertEquals(hostService.getByEmailOrElseNull(host.getEmail()), Optional.ofNullable(host));
    verify(hostMapper).findByEmail(host.getEmail());
  }
  

  @Test
  @DisplayName("새로운 host를 생성한다.")
  public void joinSuccessTest() {
    Host newHost = Host.builder()
        .id(2L)
        .firstName("테스트2")
        .lastName("테스트2")
        .nickName("테스트2")
        .phone("821011112222")
        .email("test2@gmail.com")
        .build();

    String password = "test2!@@#@R@3";

    when(hostMapper.findByEmail(newHost.getEmail())).thenReturn(Optional.empty());
    hostService.join(newHost, password);
    verify(hostMapper).findByEmail(newHost.getEmail());
    verify(hostMapper).insert(newHost);
  }

  @Test
  @DisplayName("중복 email로 host를 생성할 시 에러가 발생한다.")
  public void joinErrorTest() {
    when(hostMapper.findByEmail(host.getEmail())).thenReturn(Optional.ofNullable(host));
    assertThrows(DuplicatedUserException.class, () -> hostService.join(host, "test2!@@#@R@3"));
    verify(hostMapper).findByEmail(host.getEmail());
    verify(hostMapper, never()).insert(host);
  }

  @Test
  @DisplayName("host를 삭제한다.")
  public void removeSuccessTest() {
    when(hostMapper.findById(host.getId())).thenReturn(Optional.ofNullable(host));
    hostService.remove(host.getId());
    verify(hostMapper).delete(host.getId());
  }

  @Test
  @DisplayName("존재하지 않은 host를 삭제할 시 에러가 발생한다.")
  public void removeErrorTest() {
    when(hostMapper.findById(2L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> hostService.remove(2L));
    verify(hostMapper, never()).delete(2L);
  }

}
