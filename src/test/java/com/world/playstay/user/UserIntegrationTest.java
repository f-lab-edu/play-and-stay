package com.world.playstay.user;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.world.playstay.IntegrationTest;
import com.world.playstay.user.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@DisplayName("User Integration Test")
public class UserIntegrationTest extends IntegrationTest {

  @Test
  @DisplayName("user 생성시 201을 반환한다.")
  public void createUser201() throws Exception {
    // given
    UserDto userDto = UserDto.builder()
        .id(3L)
        .name("테스트3")
        .email("test3@gmail.com")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
        .content(objectMapper.writeValueAsString(userDto))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("user 생성시 id가 이미 존재하면 400을 반환한다.")
  public void createUser400() throws Exception {
    // given
    UserDto userDto = UserDto.builder()
        .id(2L)
        .name("테스트2")
        .email("test2@gmail.com")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
        .content(objectMapper.writeValueAsString(userDto))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errorCode").value("DuplicatedUser"))
        .andDo(print());
  }

  @Test
  @DisplayName("user 삭제시 200를 반환한다.")
  public void deleteUser200() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/users/1");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("존재하지 않은 user 삭제시 404를 반환한다.")
  public void deleteUser404() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/users/100");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorCode").value("UserNotFound"));
  }

  @Test
  @DisplayName("존재하는 user 조회시 200를 반환한다.")
  public void getUser200() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/users/1");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("존재하지 않은 user 조회시 404를 반환한다.")
  public void getUser404() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/users/100");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorCode").value("UserNotFound"));
  }

  @DisplayName("전체 user 조회시 200을 반환한다.")
  @Test
  void getUsers200() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/users");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(greaterThan(1))));
  }

}
