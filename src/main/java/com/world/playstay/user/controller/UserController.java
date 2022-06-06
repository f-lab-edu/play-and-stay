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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapstructMapper userMapstructMapper;

  private UserDto toDto(User user) {
    return userMapstructMapper.toDto(user);
  }

  private User toEntity(UserDto userDto) {
    return userMapstructMapper.toEntity(userDto);
  }

  @Operation(summary = "유저 생성")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "USER CREATED"),
      @ApiResponse(responseCode = "400", description = "DUPLICATED USER", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public void createUser(@RequestBody UserDto userDto) {
    User user = toEntity(userDto);
    userService.join(user);
  }

  @Operation(summary = "유저 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @Parameters({
      @Parameter(name = "id", description = "아이디", example = "1"),
  })
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id) {
    userService.remove(id);
  }

  @Operation(summary = "유저 조회", description = "유저 id로 유저 조회하기")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "404", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @Parameters({
      @Parameter(name = "id", description = "아이디", example = "1"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
    UserDto userDto = toDto(userService.getByIdOrElseThrow(id));
    return ResponseEntity.ok().body(userDto);
  }

  @Operation(summary = "모든 유저 조회")
  @ApiResponse(responseCode = "200", description = "OK")
  @GetMapping()
  public List<UserDto> getUsers() {
    return userService.getUsers().stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

}
