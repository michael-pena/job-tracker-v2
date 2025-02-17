package com.mpena.jobtrackerv2.components.auth.service;

import com.mpena.jobtrackerv2.components.auth.dto.LoginRequestDTO;

public interface AuthOperations {
    String generateToken(LoginRequestDTO loginRequestDTO);
}
