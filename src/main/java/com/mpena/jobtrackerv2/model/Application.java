package com.mpena.jobtrackerv2.model;

import org.hibernate.annotations.GeneratorType;

import com.mpena.jobtrackerv2.validation.ValidAcceptedValue;
import com.mpena.jobtrackerv2.validation.ValidApplicationStatus;
import com.mpena.jobtrackerv2.validation.ValidOfferForInterview;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@ValidOfferForInterview
@ValidAcceptedValue
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name="id", updatable = false, nullable = false)
    private Integer id;

    @NotEmpty
    @Size(min = 1, max=100, message = "company must be less than 100 characters")
    @Column(name = "company")
    private String company;

    @NotEmpty
    @Size(min = 1, max=100, message = "position must be less than 100 characters")
    @Column(name = "position")
    private String position;

    @NotEmpty
    @Size(min = 1, max=30, message = "date must be less than 30 characters")
    @Column(name = "date")
    private String date;

    @NotEmpty
    @Size(min = 1, max=15, message = "status must be less than 15 characters")
    @ValidApplicationStatus
    @Column(name = "status")
    private String status;
    
    @Column(name = "offer")
    private String offer;

    @Column(name = "accepted")
    private String accepted;

}
