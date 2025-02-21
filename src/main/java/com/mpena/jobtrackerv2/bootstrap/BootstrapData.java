package com.mpena.jobtrackerv2.bootstrap;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mpena.jobtrackerv2.components.application.model.Application;
import com.mpena.jobtrackerv2.components.application.model.ApplicationCSVRecord;
import com.mpena.jobtrackerv2.components.application.repository.ApplicationRepository;
import com.mpena.jobtrackerv2.components.users.model.Authority;
import com.mpena.jobtrackerv2.components.users.model.Users;
import com.mpena.jobtrackerv2.components.users.repository.AuthorityRepository;
import com.mpena.jobtrackerv2.components.users.repository.UsersRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ApplicationRepository applicationRepository;
    private final UsersRepository usersRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.default.username}")
    private String adminUsername;

    @Value("${admin.default.password}")
    private String adminPassword;

    /**
     * Runs the bootstrap data loading process.
     * If the number of records in the application repository is less than 10,
     * it retrieves data from a CSV file and persists it to the repository.
     * Used for general testing purposes and testing pagination and sorting.
     * 
     * @throws Exception if an error occurs during data loading
     */
    @Override
    public void run(String... args) throws Exception {
        
        if (applicationRepository.count() < 10) {
            List<ApplicationCSVRecord> applicationCSVRecords = getCSVData();
            persistCSVData(applicationCSVRecords);
        }

        if (usersRepository.findByUsername(adminUsername).isEmpty()) {
            
            Authority adminAuth = authorityRepository.findByAuthority("admin")
                .orElseGet(() -> authorityRepository.save(new Authority().setAuthority("admin")));

            Users adminUser = new Users()
                .setUsername(adminUsername)
                .setPassword(passwordEncoder.encode(adminPassword))
                .setAuthorities(Collections.singleton(adminAuth));

            usersRepository.save(adminUser);
            adminAuth.getUsers().add(adminUser);
        }

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

    public List<ApplicationCSVRecord> getCSVData() throws IOException {
        // This code was change to input stream to accomodate JAR deployments
        InputStream inputStream = new ClassPathResource("testapplications.csv").getInputStream();
        Path tempFile = Files.createTempFile("testapplications", ".csv");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        List<ApplicationCSVRecord> applicationCSVRecords = new CsvToBeanBuilder<ApplicationCSVRecord>(new FileReader(tempFile.toFile()))
            .withType(ApplicationCSVRecord.class)
            .build()
            .parse();

        return applicationCSVRecords;
    }
}
