package com.mpena.jobtrackerv2.service;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;

public interface ApplicationOperations {
    ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO);
}
