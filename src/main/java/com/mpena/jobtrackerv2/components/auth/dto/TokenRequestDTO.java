package com.mpena.jobtrackerv2.components.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequestDTO {

    @NotEmpty(message = "username can't be null or empty")
    @Size(min=2, max=50, message = "username must between 2 and 50 chars")
    @Schema(description = "username", example = "admin")
    String username;

    @NotEmpty(message = "password can't be null or empty")
    @Size(min=8, max=50, message="password must be greater than 8 chars.")
    @Schema(description = "password", example = "password1")
    String password;
}
