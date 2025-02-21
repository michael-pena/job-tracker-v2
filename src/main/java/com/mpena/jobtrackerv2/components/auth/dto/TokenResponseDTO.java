package com.mpena.jobtrackerv2.components.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDTO {
    String token; // access token
}
