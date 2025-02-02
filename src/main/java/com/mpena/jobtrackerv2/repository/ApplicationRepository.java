package com.mpena.jobtrackerv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpena.jobtrackerv2.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}