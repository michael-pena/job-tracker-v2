package com.mpena.jobtrackerv2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.mpena.jobtrackerv2.components.application.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.components.application.mapper.ApplicationMapper;
import com.mpena.jobtrackerv2.components.application.model.Application;
import com.mpena.jobtrackerv2.components.application.repository.ApplicationRepository;
import com.mpena.jobtrackerv2.components.application.service.ApplicationService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class Jobtrackerv2ApplicationTestService {

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
            .status("no response")
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

    @Test
    public void testListApplications() throws FileNotFoundException {

        List<ApplicationResponseDTO> fetchedResponseDTOList = applicationService.getAllApplications();
        assertNotNull(fetchedResponseDTOList);
        assertTrue(fetchedResponseDTOList.size() >= 3, "List of persisted applications size is less than expected");
    }

    public ApplicationResponseDTO toResponseDTO() {
        return applicationMapper.toDTO(savedApplication);
    }

}
