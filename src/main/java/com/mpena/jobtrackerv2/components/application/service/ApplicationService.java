package com.mpena.jobtrackerv2.components.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.components.application.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.components.application.mapper.ApplicationMapper;
import com.mpena.jobtrackerv2.components.application.model.Application;
import com.mpena.jobtrackerv2.components.application.repository.ApplicationRepository;
import com.mpena.jobtrackerv2.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService implements ApplicationOperations {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationResponseDTO createApplication(ApplicationCreateDTO createDTO) {
        Application appCreate = applicationMapper.toEntity(createDTO);
        return applicationMapper.toDTO(applicationRepository.save(appCreate));
    }

    @Override
    public ApplicationResponseDTO updateApplicationById(Long applicationId, ApplicationUpdateDTO updateDTO) {
        Application appUpdate = applicationRepository.findById(applicationId)
            .orElseThrow( () -> new NotFoundException("Application with id: " + applicationId + " Not Found"));

        appUpdate.setCompany(updateDTO.getCompany())
            .setDate(updateDTO.getDate())
            .setPosition(updateDTO.getPosition())
            .setAccepted(updateDTO.getAccepted())
            .setStatus(updateDTO.getStatus())
            .setOffer(updateDTO.getOffer());

        return applicationMapper.toDTO(applicationRepository.save(appUpdate));
    }


    @Override
    public void deleteApplication(Long applicationId) {
        Application foundApplication = applicationRepository.findById(applicationId)
            .orElseThrow( () -> new NotFoundException("Application with id: " + applicationId + " Not Found"));

        applicationRepository.delete(foundApplication);   
    }

    @Override
    public ApplicationResponseDTO getApplicationById(Long applicationId) {
        Optional<Application> optionalResponse = applicationRepository.findById(applicationId);

        if (optionalResponse.isPresent()) {
            return applicationMapper.toDTO(optionalResponse.get());
        } else {
            throw new NotFoundException("Application with id: " + applicationId + " Not Found");
        }
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationRepository.findAll()
            .stream()
            .map(applicationMapper::toDTO)
            .toList();
    }

    @Override
    public Page<ApplicationResponseDTO> getPageOfApplications(Pageable pageable) {
        return applicationRepository.findAll(pageable)
            .map(applicationMapper::toDTO);
    }
    
    
}
