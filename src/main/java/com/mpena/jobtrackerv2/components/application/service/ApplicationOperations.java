package com.mpena.jobtrackerv2.components.application.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.mpena.jobtrackerv2.components.application.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationUpdateDTO;

import org.springframework.data.domain.Page;

public interface ApplicationOperations {
    ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO);
    ApplicationResponseDTO getApplicationById(Integer applicationId);
    ApplicationResponseDTO updateApplicationById(Integer applicationId, ApplicationUpdateDTO updateDTO);
    void deleteApplication(Integer applicationId);
    List<ApplicationResponseDTO> getAllApplications();
    Page<ApplicationResponseDTO> getPageOfApplications(Pageable pageable);
}
