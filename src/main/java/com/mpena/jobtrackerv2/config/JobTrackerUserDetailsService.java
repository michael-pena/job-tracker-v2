package com.mpena.jobtrackerv2.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mpena.jobtrackerv2.components.users.model.Users;
import com.mpena.jobtrackerv2.components.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobTrackerUserDetailsService implements UserDetailsService  {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Users userFound = usersRepository.findByUsername(username).orElseThrow( () -> 
            new UsernameNotFoundException("User details not found for user: " + username));

        Set<GrantedAuthority> authorities = new HashSet<>(userFound.getAuthorities());

        return new User(userFound.getUsername(), userFound.getPassword(), authorities);
    }

}
