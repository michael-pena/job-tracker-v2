package com.mpena.jobtrackerv2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ResourceUtils;

import com.mpena.jobtrackerv2.components.application.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationResponseDTO;
import com.mpena.jobtrackerv2.components.application.dto.ApplicationUpdateDTO;
import com.mpena.jobtrackerv2.components.application.mapper.ApplicationMapper;
import com.mpena.jobtrackerv2.components.application.model.Application;
import com.mpena.jobtrackerv2.components.application.model.ApplicationCSVRecord;
import com.mpena.jobtrackerv2.components.application.repository.ApplicationRepository;
import com.mpena.jobtrackerv2.components.application.service.ApplicationService;
import com.opencsv.bean.CsvToBeanBuilder;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testListApplications() throws FileNotFoundException {
        List<ApplicationCSVRecord> applicationCSVList = getCSVData();
        assertNotNull(applicationCSVList);

        persistCSVData(applicationCSVList);

        List<ApplicationResponseDTO> fetchedResponseDTOList = applicationService.getAllApplications();
        assertNotNull(fetchedResponseDTOList);
        assertTrue(fetchedResponseDTOList.size() >= 3, "List of persisted applications size is less than expected");
    }

    public void persistCSVData(List<ApplicationCSVRecord> csvRecords) {
        csvRecords.forEach(appCSVRecord -> {
            applicationRepository.save(new Application()
                .setCompany(appCSVRecord.getCompany())
                .setAccepted(appCSVRecord.getAccepted())
                .setDate(appCSVRecord.getDate())
                .setPosition(appCSVRecord.getPosition())
                .setOffer(appCSVRecord.getPosition())
                .setStatus(appCSVRecord.getStatus())
                );
        });
    }

    public List<ApplicationCSVRecord> getCSVData() throws FileNotFoundException {
        File File = ResourceUtils.getFile("classpath:testapplications.csv");
        List<ApplicationCSVRecord> applicationCSVRecords = new CsvToBeanBuilder<ApplicationCSVRecord>(new FileReader(File))
            .withType(ApplicationCSVRecord.class)
            .build()
            .parse();

        return applicationCSVRecords;
    }

    public ApplicationResponseDTO toResponseDTO() {
        return applicationMapper.toDTO(savedApplication);
    }

}
