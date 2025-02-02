package com.mpena.jobtrackerv2;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    ApplicationCreateDTO createDTO;

    @BeforeEach
    void setup() {
        createDTO = ApplicationCreateDTO.builder()
            .company("Test Company 2")
            .position("test engineer")
            .date("1-25-2025")
            .status("applied")
            .accepted("")
            .offer("")
            .build();
    }

    @Test
    @Disabled
    public void testSaveApplication() {

        applicationService.createApplication(createDTO);
    }

    @Test
    public void testDeleteApplication() {

    }

}
