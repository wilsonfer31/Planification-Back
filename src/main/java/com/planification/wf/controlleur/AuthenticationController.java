package com.planification.wf.controlleur;

import com.planification.wf.DTO.AuthenticationResponseDTO;
import com.planification.wf.DTO.AuthenticationRequestDTO;
import com.planification.wf.DTO.RegisterRequestDTO;
import com.planification.wf.exceptions.EmailNotFoundException;
import com.planification.wf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
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
  ) throws EmailNotFoundException {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
