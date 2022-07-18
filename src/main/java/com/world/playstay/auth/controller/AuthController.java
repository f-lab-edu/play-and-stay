package com.world.playstay.auth.controller;

import com.world.playstay.auth.dto.request.LoginRequest;
import com.world.playstay.auth.dto.response.LoginResponse;
import com.world.playstay.auth.service.AuthService;
import com.world.playstay.auth.service.LoginService;
import com.world.playstay.global.exception.GlobalExceptionResponse;
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
  private final LoginService loginService;


  @Operation(summary = "로그인")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "LOGIN SUCCESSFULLY", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
      @ApiResponse(responseCode = "404", description = "USER NOT FOUND", content = @Content(schema = @Schema(implementation = UserNotFoundException.class))),
      @ApiResponse(responseCode = "400", description = "INVALID PASSWORD", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest,
      HttpServletRequest httpServletRequest) {
    Long userId = authService.validateUser(loginRequest);
    loginService.setLoginStatus(httpServletRequest, loginRequest);
    return ResponseEntity.ok().body(loginRequest.toLoginResponse(userId, LocalDateTime.now()));
  }

  @Operation(summary = "로그아웃")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "LOGOUT SUCCESSFULLY"),
  })
  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
    loginService.invalidateLoginStatus(httpServletRequest);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
