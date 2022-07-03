package com.world.playstay.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.world.playstay.ServiceTest;
import com.world.playstay.user.dao.GuestMapper;
import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.enums.AuthStatus;
import com.world.playstay.user.exception.DuplicatedUserException;
import com.world.playstay.user.exception.UserNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("Guest Service Test")
public class GuestServiceTest extends ServiceTest {

  @InjectMocks
  private GuestService guestService;

  @Mock
  private GuestMapper guestMapper;

  private Guest guest;

  @BeforeEach
  public void createGuest() {
    guest = Guest.builder()
        .id(1L)
        .firstName("테스트1")
        .lastName("테스트1")
        .nickName("테스트1")
        .phone("821011112222")
        .email("test1@gmail.com")
        .encryptedPassword("test1!@@#@R@3")
        .build();
  }

  @Test
  @DisplayName("id가 존재하는 guest를 반환한다.")
  public void getByIdOrElseThrowSuccessTest() {
    when(guestMapper.findById(guest.getId())).thenReturn(Optional.ofNullable(guest));
    assertEquals(guestService.getByIdOrElseThrow(guest.getId()), guest);
    verify(guestMapper).findById(guest.getId());
  }

  @Test
  @DisplayName("id가 존재하지 않을 시 에러가 발생한다.")
  public void getByIdOrElseThrowErrorTest() {
    when(guestMapper.findById(2L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> guestService.getByIdOrElseThrow(2L));
    verify(guestMapper).findById(2L);
  }

  @Test
  @DisplayName("id가 존재하는 guest를 반환한다.")
  public void getByIdSuccessTest() {
    when(guestMapper.findById(guest.getId())).thenReturn(Optional.ofNullable(guest));
    assertEquals(guestService.getById(guest.getId()), Optional.ofNullable(guest));
    verify(guestMapper).findById(guest.getId());
  }

  @Test
  @DisplayName("새로운 guest를 생성한다.")
  public void joinSuccessTest() {
    Guest newGuest = Guest.builder()
        .id(2L)
        .firstName("테스트2")
        .lastName("테스트2")
        .nickName("테스트2")
        .phone("821011112222")
        .email("test2@gmail.com")
        .build();

    String password = "test2!@@#@R@3";

    when(guestMapper.findByEmail(newGuest.getEmail())).thenReturn(Optional.empty());
    guestService.join(newGuest, password);
    assertEquals(newGuest.getAuthStatus(), AuthStatus.UNAUTHENTICATED.ordinal());
    assertEquals(newGuest.getCountCoupon(), 0);
    assertEquals(newGuest.getCountMonthlyStamp(), 0);
    assertNotEquals(newGuest.getEncryptedPassword(), password);
    verify(guestMapper).findByEmail(newGuest.getEmail());
    verify(guestMapper).insert(newGuest);
  }

  @Test
  @DisplayName("중복 email로 guest를 생성할 시 에러가 발생한다.")
  public void joinErrorTest() {
    when(guestMapper.findByEmail(guest.getEmail())).thenReturn(Optional.ofNullable(guest));
    assertThrows(DuplicatedUserException.class, () -> guestService.join(guest, "test2!@@#@R@3"));
    verify(guestMapper).findByEmail(guest.getEmail());
    verify(guestMapper, never()).insert(guest);
  }

}
