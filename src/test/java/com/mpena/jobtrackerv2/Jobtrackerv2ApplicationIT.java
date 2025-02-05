package com.mpena.jobtrackerv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.mapper.ApplicationMapper;
import com.mpena.jobtrackerv2.model.Application;
import com.mpena.jobtrackerv2.repository.ApplicationRepository;
import com.mpena.jobtrackerv2.service.ApplicationService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class Jobtrackerv2ApplicationIT {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationMapper applicationMapper;

    ApplicationCreateDTO createDTO;
    Application savedApplication;

    @BeforeEach
    @Rollback    
    void setup() {
        createDTO = ApplicationCreateDTO.builder()
            .company("Test Company 2")
            .position("test engineer")
            .date("1-25-2025")
            .status("applied")
            .accepted("")
            .offer("")
            .build();
        
        // savedDTO = applicationMapper.toDTO(applicationRepository.save(applicationMapper.toEntity(createDTO)));
        savedApplication = applicationRepository.save(applicationMapper.toEntity(createDTO));
    }

    @Test
    public void testSaveApplication() { 
        assertNotNull(savedApplication.getId());
        Optional<Application> appFound = applicationRepository.findById(savedApplication.getId());
        assertNotNull(appFound.get());
    }

    @Test
    public void testDeleteApplication() {
        assertNotNull(savedApplication.getId());

        applicationRepository.deleteById(savedApplication.getId());
        Optional<Application> emptyApplication = applicationRepository.findById(savedApplication.getId());
        assertFalse(emptyApplication.isPresent());
    }


    @Test
    public void testUpdateApplication() {
        assertNotNull(savedApplication.getId());

        savedApplication.setStatus("rejected");
        ApplicationUpdateDTO updateDTO = ApplicationUpdateDTO.builder()
            .company(savedApplication.getCompany())
            .offer(savedApplication.getOffer())
            .position(savedApplication.getPosition())
            .date(savedApplication.getDate())
            .status(savedApplication.getStatus())
            .accepted(savedApplication.getAccepted())
            .build();

        ApplicationResponseDTO updatedSavedDTO = applicationService.updateApplicationById(savedApplication.getId(), updateDTO);
        assertNotNull(updatedSavedDTO);
        
        assertEquals(savedApplication.getId(), updatedSavedDTO.getId());
        assertEquals(savedApplication.getCompany(), updatedSavedDTO.getCompany());
        assertEquals("rejected", updatedSavedDTO.getStatus());
    }


    public ApplicationResponseDTO toResponseDTO() {
        return applicationMapper.toDTO(savedApplication);
    }

}
