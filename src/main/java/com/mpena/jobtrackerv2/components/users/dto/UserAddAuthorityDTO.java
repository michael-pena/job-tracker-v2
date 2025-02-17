package com.mpena.jobtrackerv2.components.users.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddAuthorityDTO {
    private String username;
    private String authority;
}
