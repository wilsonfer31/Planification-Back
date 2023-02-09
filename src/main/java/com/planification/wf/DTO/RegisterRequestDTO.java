package com.planification.wf.DTO;

import com.planification.wf.entity.Role_Enum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
  private String username;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role_Enum role;
}
