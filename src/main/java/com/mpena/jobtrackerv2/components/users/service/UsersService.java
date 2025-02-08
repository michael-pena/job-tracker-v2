package com.mpena.jobtrackerv2.components.users.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.components.users.dto.UserCreateDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserResponseDTO;
import com.mpena.jobtrackerv2.components.users.dto.UserUpdateDTO;
import com.mpena.jobtrackerv2.components.users.mapper.UsersMapper;
import com.mpena.jobtrackerv2.components.users.model.Users;
import com.mpena.jobtrackerv2.components.users.repository.UsersRepository;
import com.mpena.jobtrackerv2.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements UsersOperations {

    private final UsersRepository userRepository;
    private final UsersMapper userMapper;

    @Override
    public UserResponseDTO createUsers(UserCreateDTO createDTO) {
        
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
