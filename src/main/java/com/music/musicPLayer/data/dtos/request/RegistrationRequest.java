package com.music.musicPLayer.data.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private Long userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
}
