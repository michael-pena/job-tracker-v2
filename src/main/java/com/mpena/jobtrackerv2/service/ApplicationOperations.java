package com.mpena.jobtrackerv2.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.dto.ApplicationUpdateDTO;

public interface ApplicationOperations {
    ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO);
    ApplicationResponseDTO getApplicationById(Integer applicationId);
    ApplicationResponseDTO updateApplicationById(Integer applicationId, ApplicationUpdateDTO updateDTO);
    void deleteApplication(Integer applicationId);
    List<ApplicationResponseDTO> getAllApplications();
    Page<ApplicationResponseDTO> getPageOfApplications(Pageable pageable);
}
