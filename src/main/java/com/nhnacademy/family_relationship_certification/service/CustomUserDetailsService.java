package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.entity.Users;
import com.nhnacademy.family_relationship_certification.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        return new User(user.getUserId(), user.getPwd(),
        Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority())));
    }
}
