package com.music.musicPLayer.service;

import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.AuthenticationResponse;
import com.music.musicPLayer.dtos.response.RegistrationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test void testThatAUserCanRegister(){
        RegistrationRequest request = new RegistrationRequest();
        request.setName("name");
        request.setPassword("password");
        request.setEmail("email@gmail.com");
        request.setPhoneNumber("090XXX");
        AuthenticationResponse response = userService.register(request);
       assertThat(response).isNotNull();
    }

    @Test void testThatAUserCanRegister2(){
        RegistrationRequest request = new RegistrationRequest();
        request.setName("namenamenamenamenamenamenamename");
        request.setPassword("password");
        request.setEmail("email@gmail.com");
        request.setPhoneNumber("090XXX");
        AuthenticationResponse response = userService.register(request);
        assertThat(response).isNotNull();
    }

}