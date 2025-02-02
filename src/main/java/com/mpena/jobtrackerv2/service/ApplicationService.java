package com.mpena.jobtrackerv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.mapper.ApplicationMapper;
import com.mpena.jobtrackerv2.model.Application;
import com.mpena.jobtrackerv2.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService implements ApplicationOperations {

    @Autowired
    private final ApplicationRepository applicationRepository;

    @Autowired
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO) {
        Application appcreate = applicationMapper.toEntity(createDTO);
        System.out.println("Application Created: " + appcreate);

        return applicationMapper.toDTO(applicationRepository.save(appcreate));
    }
}
