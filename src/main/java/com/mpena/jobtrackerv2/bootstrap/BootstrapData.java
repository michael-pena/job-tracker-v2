package com.mpena.jobtrackerv2.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.mpena.jobtrackerv2.components.application.model.Application;
import com.mpena.jobtrackerv2.components.application.model.ApplicationCSVRecord;
import com.mpena.jobtrackerv2.components.application.repository.ApplicationRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ApplicationRepository applicationRepository;

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
}
