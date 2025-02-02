package com.mpena.jobtrackerv2.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationUpdateDTO {

    private String company;
    private String position;
    private String date;
    private String status;
    private String offer;
    private String accepted;
}
