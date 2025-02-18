package com.mpena.jobtrackerv2.components.users.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.components.users.dto.UserAddAuthorityDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserAddAuthorityResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserUpdateDTO;
import com.mpena.jobtrackerv2.components.users.mapper.UsersMapper;
import com.mpena.jobtrackerv2.components.users.model.Authority;
import com.mpena.jobtrackerv2.components.users.model.Users;
import com.mpena.jobtrackerv2.components.users.repository.AuthorityRepository;
import com.mpena.jobtrackerv2.components.users.repository.UsersRepository;
import com.mpena.jobtrackerv2.exceptions.AlreadyExistsException;
import com.mpena.jobtrackerv2.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements UsersOperations {

    private final UsersRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UsersMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public void deleteUsers(long userId) {
        Users foundUser = userRepository.findById(userId).orElseThrow( () -> {
            throw new NotFoundException("User with id: " + userId + " was not found.");
        });

        userRepository.delete(foundUser);
    }

    @Override
    @Transactional
    public UserAddAuthorityResponseDTO addUserAuthortiy(UserAddAuthorityDTO addAuthorityDTO) {

        String username = addAuthorityDTO.getUsername();
        String authority = addAuthorityDTO.getAuthority();

        Users userFound = userRepository.findByUsername(username).orElseThrow( () -> {
            throw new NotFoundException("User with username: " + username + " not found.");
        });

        boolean roleAlreadyExists = userFound.getAuthorities()
            .stream()
            .anyMatch( userAuthority -> userAuthority.getAuthority().equals(authority));

        if (roleAlreadyExists) {
            throw new AlreadyExistsException("Role: " + authority + " already exists for user: " + username);
        }

        //TODO: check if authority is in list of enums
        Authority savedAuthority = authorityRepository.findByAuthority(authority)
            .orElseGet(() -> authorityRepository.save(new Authority().setAuthority(authority)));

        savedAuthority.getUsers().add(userFound);
        userFound.getAuthorities().add(savedAuthority);

        return UserAddAuthorityResponseDTO.builder()
            .username(userFound.getUsername())
            .authorities(userFound.getAuthorities().stream().map(userAuthority -> userAuthority.getAuthority()).collect(Collectors.toSet()))
            .build();
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
