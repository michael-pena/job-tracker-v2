package com.mpena.jobtrackerv2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mpena.jobtrackerv2.dto.ApplicationCreateDTO;
import com.mpena.jobtrackerv2.service.ApplicationService;

@SpringBootTest
public class Jobtrackerv2ApplicationIT {

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void testSaveApplication() {

        ApplicationCreateDTO createDTO = ApplicationCreateDTO.builder()
            .company("Test Company 2")
            .position("test engineer")
            .date("1-25-2025")
            .status("applied")
            .accepted("")
            .offer("")
            .build();

        applicationService.createApplication(createDTO);
    }

}
