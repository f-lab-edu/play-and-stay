package com.world.playstay.auth.controller;

import com.world.playstay.auth.dto.request.LoginRequest;
import com.world.playstay.auth.dto.request.LogoutRequest;
import com.world.playstay.auth.dto.response.LoginResponse;
import com.world.playstay.auth.service.AuthService;
import com.world.playstay.global.exception.GlobalExceptionResponse;
import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.entity.Host;
import com.world.playstay.user.enums.UserType;
import com.world.playstay.user.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth")
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "로그인 Host")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "LOGIN SUCCESSFULLY", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
      @ApiResponse(responseCode = "404", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = UserNotFoundException.class))),
      @ApiResponse(responseCode = "400", description = "INVALID PASSWORD", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @PostMapping("/login/host")
  public ResponseEntity<LoginResponse> loginHost(@RequestBody @Validated LoginRequest loginRequest,
      HttpServletRequest httpServletRequest) {
    Host host = (Host) authService.login(httpServletRequest, loginRequest, UserType.HOST);
    return ResponseEntity.ok().body(LoginResponse.from(host, LocalDateTime.now()));
  }

  @Operation(summary = "로그인 Guest")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "LOGIN SUCCESSFULLY", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
      @ApiResponse(responseCode = "404", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = UserNotFoundException.class))),
      @ApiResponse(responseCode = "400", description = "INVALID PASSWORD", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @PostMapping("/login/guest")
  public ResponseEntity<LoginResponse> loginGuest(@RequestBody @Validated LoginRequest loginRequest,
      HttpServletRequest httpServletRequest) {
    Guest guest = (Guest) authService.login(httpServletRequest, loginRequest, UserType.GUEST);
    return ResponseEntity.ok().body(LoginResponse.from(guest, LocalDateTime.now()));
  }

  @Operation(summary = "로그아웃")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "LOGOUT SUCCESSFULLY"),
  })
  @PostMapping("/logout")
  public ResponseEntity<Void> logout(@RequestBody @Validated LogoutRequest logoutRequest,
      HttpServletRequest httpServletRequest) {
    authService.logout(httpServletRequest, logoutRequest.getLoginMethod());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
