package com.se.video.library.services.impl;

import com.se.video.library.errors.exception.AlreadyExistException;
import com.se.video.library.errors.exception.AppException;
import com.se.video.library.dao.models.Role;
import com.se.video.library.dao.models.RoleName;
import com.se.video.library.dao.models.User;
import com.se.video.library.dao.repository.RoleRepository;
import com.se.video.library.dao.repository.UserRepository;
import com.se.video.library.payload.request.LoginRequest;
import com.se.video.library.payload.request.SignUpRequest;
import com.se.video.library.payload.response.JwtAuthenticationResponse;
import com.se.video.library.payload.response.JwtResponse;
import com.se.video.library.security.JwtTokenProvider;
import com.se.video.library.security.UserPrincipal;
import com.se.video.library.services.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public JwtAuthenticationResponse signUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        JwtAuthenticationResponse body = new JwtAuthenticationResponse(jwt);

        log.info("");
        return body;
    }

    @Override
    public void registerUser(@Valid SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AlreadyExistException("user", "user name", signUpRequest.getUsername());
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("user", "user email", signUpRequest.getEmail());
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        log.info("User created. Name:{}, role:{}", user.getName(), userRole.getName());
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse(jwt,"Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
        return jwtResponse;
    }
}
