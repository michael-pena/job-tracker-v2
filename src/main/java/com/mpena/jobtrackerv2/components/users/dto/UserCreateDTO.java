package com.mpena.jobtrackerv2.components.users.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCreateDTO {
    private int id;
    
    private String username;

    private String password;
}
