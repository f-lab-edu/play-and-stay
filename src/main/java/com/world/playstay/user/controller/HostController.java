package com.world.playstay.user.controller;

import com.world.playstay.global.exception.GlobalExceptionResponse;
import com.world.playstay.user.dto.request.CreateHostRequest;
import com.world.playstay.user.dto.response.HostResponse;
import com.world.playstay.user.entity.Host;
import com.world.playstay.user.mapper.HostMapstructMapper;
import com.world.playstay.user.service.HostService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "hosts")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hosts")
public class HostController {

  private final HostService hostService;
  private final HostMapstructMapper hostMapstructMapper;


  private Host toEntity(CreateHostRequest hostRequest) {
    return hostMapstructMapper.toHost(hostRequest);
  }

  private HostResponse toResponse(Host host) {
    return hostMapstructMapper.toResponse(host);
  }

  @Operation(summary = "회원가입")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "HOST CREATED"),
      @ApiResponse(responseCode = "400", description = "DUPLICATED HOST", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public void create(@RequestBody @Validated CreateHostRequest createHostRequest) {
    Host host = toEntity(createHostRequest);
    hostService.join(host, createHostRequest.getPassword());
  }

  @Operation(summary = "호스트 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "HOST NOT FOUND", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @Parameters({
      @Parameter(name = "id", description = "아이디", example = "1"),
  })
  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    hostService.remove(id);
  }

  @Operation(summary = "호스트 조회", description = "호스트 id로 호스트 조회하기")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HostResponse.class))),
      @ApiResponse(responseCode = "404", description = "HOST NOT FOUND", content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class))),
  })
  @Parameters({
      @Parameter(name = "id", description = "아이디", example = "1"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<HostResponse> get(@PathVariable("id") Long id) {
    HostResponse hostResponse = toResponse(hostService.getByIdOrElseThrow(id));
    return ResponseEntity.ok().body(hostResponse);
  }

  @Operation(summary = "모든 호스트 조회")
  @ApiResponse(responseCode = "200", description = "OK")
  @GetMapping()
  public List<HostResponse> getList() {
    return hostService.getAll().stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

}
