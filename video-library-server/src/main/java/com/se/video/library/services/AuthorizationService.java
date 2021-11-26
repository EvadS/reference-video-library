package com.se.video.library.services;

import com.se.video.library.payload.request.LoginRequest;
import com.se.video.library.payload.request.SignUpRequest;
import com.se.video.library.payload.response.JwtAuthenticationResponse;
import com.se.video.library.payload.response.JwtResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthorizationService {

    /**
     * create authenticate user in system
     * @param loginRequest model to create login
     * @return token to communicate with this system
     */
    JwtAuthenticationResponse signUser(LoginRequest loginRequest);

    void registerUser(@Valid @RequestBody SignUpRequest signUpRequest);

    JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest);
}
