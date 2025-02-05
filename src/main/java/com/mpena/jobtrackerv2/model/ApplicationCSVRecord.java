package com.mpena.jobtrackerv2.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationCSVRecord {

    @CsvBindByName
    private String company;

    @CsvBindByName
    private String position;

    @CsvBindByName
    private String date;

    @CsvBindByName
    private String status;
    
    @CsvBindByName
    private String offer;

    @CsvBindByName
    private String accepted;
}
