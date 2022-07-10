package com.world.playstay.user;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.world.playstay.IntegrationTest;
import com.world.playstay.user.dto.request.HostRequest.Creation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@DisplayName("Host Integration Test")
public class HostIntegrationTest extends IntegrationTest {

  @Test
  @DisplayName("host 생성시 201을 반환한다.")
  public void createHost201() throws Exception {
    // given
    Creation hostRequest = Creation.builder()
        .firstName("테스트3")
        .lastName("테스트3")
        .nickName("테스트3")
        .phone("821011112222")
        .email("test3@gmail.com")
        .password("test3!@@#@R@3")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hosts")
        .content(objectMapper.writeValueAsString(hostRequest))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("host 생성시 email이 이미 존재하면 400을 반환한다.")
  public void createHostWithDuplicateEmail400() throws Exception {
    // given
    Creation hostRequest = Creation.builder()
        .firstName("테스트2")
        .lastName("테스트2")
        .nickName("테스트2")
        .phone("821011112222")
        .email("test2@gmail.com")
        .password("test2!@@#@R@3")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hosts")
        .content(objectMapper.writeValueAsString(hostRequest))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errorCode").value("DuplicatedUser"))
        .andDo(print());
  }

  @Test
  @DisplayName("host 생성시 비밀번호 정규식과 일치하지 않으면 MethodArgumentNotValidException과 400을 반환한다.")
  public void createHostWithValidationFail400() throws Exception {
    // given
    Creation hostRequest = Creation.builder()
        .firstName("테스트2")
        .lastName("테스트2")
        .nickName("테스트2")
        .phone("821011112222")
        .email("test3@gmail.com")
        .password("test2")
        .build();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hosts")
        .content(objectMapper.writeValueAsString(hostRequest))
        .contentType(MediaType.APPLICATION_JSON);

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errorCode").value("MethodArgumentNotValid"))
        .andDo(print());
  }

  @Test
  @DisplayName("host 삭제시 200를 반환한다.")
  public void deleteHost200() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/hosts/1");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("존재하지 않은 host 삭제시 404를 반환한다.")
  public void deleteHost404() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/hosts/100");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorCode").value("UserNotFound"));
  }

  @Test
  @DisplayName("존재하는 host 조회시 200를 반환한다.")
  public void getHost200() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/hosts/1");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("존재하지 않은 host 조회시 404를 반환한다.")
  public void getHost404() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/hosts/100");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorCode").value("UserNotFound"));
  }

  @DisplayName("전체 host 조회시 200을 반환한다.")
  @Test
  void getHosts200() throws Exception {

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/hosts");

    // then
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(greaterThan(1))));
  }

}
