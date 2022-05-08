package com.world.playstay.user.controller;

import com.world.playstay.user.dto.UserDto;
import com.world.playstay.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ResponseBody
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public void create(UserDto userDto) {
    userService.join(userDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id) {
    UserDto userDto = userService.findUser(id);
    return ResponseEntity.ok().body(userDto);
  }

}
