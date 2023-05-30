package com.planification.wf.models.DTO;

import com.planification.wf.models.enums.Role_Enum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDTO {
    private Long Id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role_Enum role;
}
