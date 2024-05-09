package com.music.musicPLayer.service;


import com.music.musicPLayer.data.dtos.request.RegistrationRequest;
import com.music.musicPLayer.data.dtos.response.RegistrationResponse;

public interface UserService {
    RegistrationResponse register(RegistrationRequest request);
}
