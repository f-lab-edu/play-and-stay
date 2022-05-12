package com.world.playstay.user.controller;

import com.world.playstay.user.dto.UserDto;
import com.world.playstay.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostMapping()
  public void create(UserDto userDto) {
    userService.join(userDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id) {
    UserDto userDto = userService.findUser(id);
    logger.trace("trace log");
    logger.debug("debug log");
    logger.info("info log");
    logger.warn("warn log");
    logger.error("error log");
    return ResponseEntity.ok().body(userDto);
  }

}
