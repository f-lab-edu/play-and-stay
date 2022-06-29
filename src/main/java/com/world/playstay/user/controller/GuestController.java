package com.world.playstay.user.controller;

import com.world.playstay.global.exception.GlobalExceptionResponse;
import com.world.playstay.user.dto.request.GuestRequest;
import com.world.playstay.user.dto.response.GuestResponse;
import com.world.playstay.user.entity.Guest;
import com.world.playstay.user.mapper.GuestMapstructMapper;
import com.world.playstay.user.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "guests")
@RequiredArgsConstructor
@RestController
@RequestMapping("/guests")
public class GuestController {

  private final GuestService guestService;
  private final GuestMapstructMapper guestMapstructMapper;


  private Guest toEntity(GuestRequest.Creation guestRequest) {
    return guestMapstructMapper.toGuest(guestRequest);
  }

  private GuestResponse toResponse(Guest guest) {
    return guestMapstructMapper.toResponse(guest);
  }

  @Operation(summary = "회원가입")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "GUEST CREATED"),
      @ApiResponse(responseCode = "400", description = "DUPLICATED GUEST", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public void create(@RequestBody @Validated GuestRequest.Creation guestRequest) {
    Guest guest = toEntity(guestRequest);
    guestService.join(guest, guestRequest.getPassword());
  }

}
