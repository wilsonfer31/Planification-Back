package com.planification.wf.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {

  private String email;
  private String token;
}
