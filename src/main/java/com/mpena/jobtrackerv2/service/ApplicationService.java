package com.mpena.jobtrackerv2.service;

import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService implements ApplicationOperations {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO) {
        return null;
    }
}
