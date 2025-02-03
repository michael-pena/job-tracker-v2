package com.mpena.jobtrackerv2.service;

import java.util.List;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;

public interface ApplicationOperations {
    ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO);
    ApplicationResponseDTO getApplicationById(Integer applicationId);
    void deleteApplication(Integer applicationId);
    List<ApplicationResponseDTO> getAllApplications();
}
