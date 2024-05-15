package com.music.musicPLayer.service;


import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.RegistrationResponse;

public interface UserService {
    RegistrationResponse register(RegistrationRequest request);
}
