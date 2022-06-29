package com.world.playstay.user;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.world.playstay.IntegrationTest;
import com.world.playstay.user.dto.request.GuestRequest.Creation;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@DisplayName("Guest Integration Test")
public class GuestIntegrationTest extends IntegrationTest {

  @Test
  @DisplayName("guest 생성시 201을 반환한다.")
  public void createGuest201() throws Exception {
    // given
    Creation guestRequest = Creation.builder()
        .firstName("테스트3")
        .lastName("테스트3")
        .nickName("테스트3")
        .phone("821011112222")
        .email("test3@gmail.com")
        .birth(LocalDate.now())
        .password("test3!@@#@R@3")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guests")
        .content(objectMapper.writeValueAsString(guestRequest))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("guest 생성시 email이 이미 존재하면 DuplicatedUserException과 400을 반환한다.")
  public void createGuestWithDuplicateEmail400() throws Exception {
    // given
    Creation guestRequest = Creation.builder()
        .firstName("테스트2")
        .lastName("테스트2")
        .nickName("테스트2")
        .phone("821011112222")
        .email("test2@gmail.com")
        .birth(LocalDate.now())
        .password("test2!@@#@R@3")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guests")
        .content(objectMapper.writeValueAsString(guestRequest))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errorCode").value("DuplicatedUser"))
        .andDo(print());
  }

  @Test
  @DisplayName("guest 생성시 비밀번호 정규식과 일치하지 않으면 MethodArgumentNotValidException과 400을 반환한다.")
  public void createGuestWithValidationFail400() throws Exception {
    // given
    Creation guestRequest = Creation.builder()
        .firstName("테스트2")
        .lastName("테스트2")
        .nickName("테스트2")
        .phone("821011112222")
        .email("test3@gmail.com")
        .birth(LocalDate.now())
        .password("test3")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guests")
        .content(objectMapper.writeValueAsString(guestRequest))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errorCode").value("MethodArgumentNotValid"))
        .andDo(print());
  }

}
