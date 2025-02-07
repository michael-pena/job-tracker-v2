package com.mpena.jobtrackerv2.components.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpena.jobtrackerv2.components.users.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
