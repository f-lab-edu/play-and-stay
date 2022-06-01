package com.world.playstay.user.controller;

import com.world.playstay.global.error.GlobalExceptionResponse;
import com.world.playstay.user.dto.UserDto;
import com.world.playstay.user.entity.User;
import com.world.playstay.user.mapper.UserMapstructMapper;
import com.world.playstay.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapstructMapper userMapstructMapper;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Operation(summary = "유저 생성")
  @ApiResponse(responseCode = "200")
  @PostMapping()
  public void create(UserDto userDto) {
    User user = userMapstructMapper.toEntity(userDto);
    userService.join(user);
  }

  @Operation(summary = "유저 조회", description = "유저 id로 유저 조회하기")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "404", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
      @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @Parameters({
      @Parameter(name = "id", description = "아이디", example = "1"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id) {
    User user = userService.findUser(id);
    UserDto userDto = userMapstructMapper.toDto(user);
    return ResponseEntity.ok().body(userDto);
  }

}
