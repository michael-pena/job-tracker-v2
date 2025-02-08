package com.mpena.jobtrackerv2.components.users.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDTO {
    private long id;
    
    private String username;

    private String password;
}
