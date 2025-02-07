package com.mpena.jobtrackerv2.components.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationStatus {

    NO_RESPONSE("no response"),
    REJECTED("rejected"), 
    INTERVIEW("interview");
    
    private final String value;

    public static boolean contains(String value) {
        for (ApplicationStatus status : ApplicationStatus.values()) {
            if (status.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
