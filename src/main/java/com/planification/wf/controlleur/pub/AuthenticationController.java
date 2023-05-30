package com.planification.wf.controlleur.pub;

import com.planification.wf.models.DTO.AuthenticationResponseDTO;
import com.planification.wf.models.DTO.AuthenticationRequestDTO;
import com.planification.wf.models.DTO.RegisterRequestDTO;
import com.planification.wf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDTO> register(
      @RequestBody RegisterRequestDTO request
  ) throws Exception {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDTO> authenticate(
      @RequestBody AuthenticationRequestDTO request
  ) throws Exception {
    return ResponseEntity.ok(service.authenticate(request));
  }



}
