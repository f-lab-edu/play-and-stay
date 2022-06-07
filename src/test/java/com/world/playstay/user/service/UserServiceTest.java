package com.world.playstay.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.world.playstay.ServiceTest;
import com.world.playstay.user.dao.UserMapper;
import com.world.playstay.user.entity.User;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("User Service Test")
public class UserServiceTest extends ServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserMapper userMapper;

  private User user;

  @BeforeEach
  public void createUser() {
    user = User.builder()
        .id(1L)
        .name("test1")
        .email("test1@gmail.com")
        .build();
  }

  @Test
  @DisplayName("id가 존재하는 유저를 반환한다.")
  public void getByIdOrElseThrowSuccessTest() {
    when(userMapper.findById(user.getId())).thenReturn(Optional.ofNullable(user));
    assertEquals(userService.getByIdOrElseThrow(user.getId()), user);
    verify(userMapper).findById(user.getId());
  }

  @Test
  @DisplayName("id가 존재하지 않을 시 에러가 발생한다.")
  public void getByIdOrElseThrowErrorTest() {
    when(userMapper.findById(2L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userService.getByIdOrElseThrow(2L));
    verify(userMapper).findById(2L);
  }

  @Test
  @DisplayName("id가 존재하는 유저를 반환한다.")
  public void getByIdOrElseNullSuccessTest() {
    when(userMapper.findById(user.getId())).thenReturn(Optional.ofNullable(user));
    assertEquals(userService.getByIdOrElseNull(user.getId()), Optional.ofNullable(user));
    verify(userMapper).findById(user.getId());
  }

  @Test
  @DisplayName("새로운 유저를 생성한다.")
  public void joinSuccessTest() {
    User newUser = User.builder()
        .id(2L)
        .name("test2")
        .email("test2@gmail.com")
        .build();

    when(userMapper.findById(newUser.getId())).thenReturn(Optional.empty());
    userService.join(newUser);
    verify(userMapper).findById(newUser.getId());
    verify(userMapper).insert(newUser);
  }

  @Test
  @DisplayName("중복 id로 유저를 생성할 시 에러가 발생한다.")
  public void joinErrorTest() {
    when(userMapper.findById(user.getId())).thenReturn(Optional.ofNullable(user));
    assertThrows(DuplicatedUserException.class, () -> userService.join(user));
    verify(userMapper).findById(user.getId());
    verify(userMapper, never()).insert(user);
  }

  @Test
  @DisplayName("유저를 삭제한다.")
  public void removeSuccessTest() {
    when(userMapper.findById(user.getId())).thenReturn(Optional.ofNullable(user));
    userService.remove(user.getId());
    verify(userMapper).delete(user.getId());
  }

  @Test
  @DisplayName("존재하지 않은 유저를 삭제할 시 에러가 발생한다.")
  public void removeErrorTest() {
    when(userMapper.findById(2L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userService.remove(2L));
    verify(userMapper, never()).delete(2L);
  }

}
