package com.music.musicPLayer.auth;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.music.musicPLayer.config.UpdateModel;
import com.music.musicPLayer.data.model.FileModel;
import com.music.musicPLayer.data.model.Video;
import com.music.musicPLayer.data.repository.VideoRepository;
import com.music.musicPLayer.dtos.request.AuthenticationRequest;
import com.music.musicPLayer.dtos.request.RegisterRequest;
import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.AuthenticationResponse;
import com.music.musicPLayer.exception.ControllerException;
import com.music.musicPLayer.exception.ResourceNotFound;
import com.music.musicPLayer.exception.UserDoesNotExistException;
import com.music.musicPLayer.service.FileInterface;
import com.music.musicPLayer.service.UserService;
import com.music.musicPLayer.service.VideoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws UserDoesNotExistException {
return ResponseEntity.ok(userService.authenticate(request));
    }





}
