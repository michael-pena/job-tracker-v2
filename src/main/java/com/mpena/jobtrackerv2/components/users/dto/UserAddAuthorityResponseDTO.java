package com.mpena.jobtrackerv2.components.users.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserAddAuthorityResponseDTO {
    private String username;
    Set<String> authorities;

}
