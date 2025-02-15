package com.mpena.jobtrackerv2.components.users.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.components.users.dto.LoginRequestDTO;
import com.mpena.jobtrackerv2.components.users.dto.LoginResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserUpdateDTO;
import com.mpena.jobtrackerv2.components.users.mapper.UsersMapper;
import com.mpena.jobtrackerv2.components.users.model.Users;
import com.mpena.jobtrackerv2.components.users.repository.UsersRepository;
import com.mpena.jobtrackerv2.exceptions.AlreadyExistsException;
import com.mpena.jobtrackerv2.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements UsersOperations {

    private final UsersRepository userRepository;
    private final UsersMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDTO createUsers(UserCreateDTO createDTO) {

        //TODO: create extra field in dto for confirming password - check passwords match before registering

        String usernameDTO = createDTO.getUsername();

        Optional<Users> userFound =  userRepository.findByUsername(usernameDTO);

        if (userFound.isPresent()) {
            throw new AlreadyExistsException("User with username: " + usernameDTO + " already exists.");
        }

        String hashPassword = passwordEncoder.encode(createDTO.getPassword());
        createDTO.setPassword(hashPassword);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(createDTO)));
    }

    public LoginResponseDTO apiLogin(LoginRequestDTO authRequestDTO) {

        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(authRequestDTO.getUsername(), 
            authRequestDTO.getPassword());

        Authentication authenticationResponse = authenticationManager.authenticate(authRequest);

        if (authenticationResponse.isAuthenticated() && authenticationResponse != null) {
            //TODO: auth user
        }

        return null;
    }

    @Override
    public void deleteUsers(long userId) {
        Users foundUser = userRepository.findById(userId).orElseThrow( () -> {
            throw new NotFoundException("User with id: " + userId + " was not found.");
        });

        userRepository.delete(foundUser);
    }

    @Override
    public UserResponseDTO getUsersByUsername(String username) {
        Optional<Users> foundUser = userRepository.findByUsername(username);

        if (foundUser.isPresent()) {
            return userMapper.toDto(foundUser.get());
        }

        throw new NotFoundException("User with username: " + username + " was not found.");
    }

    @Override
    public UserResponseDTO updateUsers(long userId, UserUpdateDTO updateDTO) {
        
        Users foundUser = userRepository.findById(userId).orElseThrow( () -> {
            throw new NotFoundException("User with id: " + userId + " was not found.");
        });

        foundUser.setUsername(updateDTO.getUsername())
            .setPassword(updateDTO.getPassword());

        return userMapper.toDto(userRepository.save(foundUser));
    }   
}
