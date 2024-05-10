package com.music.musicPLayer.service;

import com.music.musicPLayer.data.dtos.request.RegistrationRequest;
import com.music.musicPLayer.data.dtos.response.RegistrationResponse;
import com.music.musicPLayer.data.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
       RegistrationResponse response = userService.register(request);
       assertThat(response).isNotNull();
    }

}