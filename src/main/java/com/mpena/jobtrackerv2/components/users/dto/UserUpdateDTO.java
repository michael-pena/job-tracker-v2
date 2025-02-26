package com.mpena.jobtrackerv2.components.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserUpdateDTO {
    
    @JsonIgnore
    private long id;
    
    @NotEmpty(message = "username can't be null or empty")
    private String username;

    @NotEmpty(message = "password can't be null or empty")
    private String password;
}
