package com.mpena.jobtrackerv2.components.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDTO {

    @JsonIgnore
    private String id;

    private String username;

    @JsonIgnore
    private String password;
}
