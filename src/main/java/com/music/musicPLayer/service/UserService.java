package com.music.musicPLayer.service;


import com.music.musicPLayer.dtos.request.AuthenticationRequest;
import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.AuthenticationResponse;
import com.music.musicPLayer.dtos.response.RegistrationResponse;
import com.music.musicPLayer.exception.UserDoesNotExistException;

public interface UserService {
    AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request) throws UserDoesNotExistException;
}
