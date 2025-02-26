package com.mpena.jobtrackerv2.components.users.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddAuthorityDTO {
    
    @NotEmpty(message = "username can't be null or empty")
    @Size(min=2, max=50, message = "username must between 2 and 50 chars")
    private String username;

    @NotEmpty(message = "authority can't be null or empty")
    @Size(min=2, max=50, message = "authority must between 2 and 20 chars")
    private String authority;
}
