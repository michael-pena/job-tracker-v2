package com.mpena.jobtrackerv2.components.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpena.jobtrackerv2.components.application.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}