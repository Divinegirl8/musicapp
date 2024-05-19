package com.music.musicPLayer.auth;

import com.music.musicPLayer.dtos.request.AuthenticationRequest;
import com.music.musicPLayer.dtos.request.RegisterRequest;
import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.AuthenticationResponse;
import com.music.musicPLayer.exception.UserDoesNotExistException;
import com.music.musicPLayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request){
        return ResponseEntity.ok(userService.register(request));

    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) throws UserDoesNotExistException {
return ResponseEntity.ok(userService.authenticate(request));
    }

}
