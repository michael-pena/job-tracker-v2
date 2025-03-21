package com.mpena.jobtrackerv2.components.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCreateDTO {

    @JsonIgnore
    private long id;
    
    @NotEmpty(message = "username can't be null or empty")
    @Size(min=2, max=50, message = "username must between 2 and 50 chars")
    private String username;

    @NotEmpty(message = "password can't be null or empty")
    @Size(min=8, max=50, message="password must be greater than 8 chars.")
    private String password;
}
