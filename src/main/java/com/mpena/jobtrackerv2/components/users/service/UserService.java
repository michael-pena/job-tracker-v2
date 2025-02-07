package com.mpena.jobtrackerv2.components.users.service;

import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.components.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository userRepository;

}
