package com.mpena.jobtrackerv2.components.auth.service;

import com.mpena.jobtrackerv2.components.auth.dto.TokenRequestDTO;
import com.mpena.jobtrackerv2.components.auth.dto.TokenResponseDTO;

public interface AuthOperations {
    TokenResponseDTO generateToken(TokenRequestDTO tokenRequestDTO);
}
