package com.music.musicPLayer.implementation;

import com.music.musicPLayer.data.model.Role;
import com.music.musicPLayer.data.model.User;
import com.music.musicPLayer.dtos.request.AuthenticationRequest;
import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.AuthenticationResponse;
import com.music.musicPLayer.dtos.response.RegistrationResponse;
import com.music.musicPLayer.data.repository.UserRepository;
import com.music.musicPLayer.exception.UserDoesNotExistException;
import com.music.musicPLayer.service.JwtService;
import com.music.musicPLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceApp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserDoesNotExistException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()) .orElseThrow(()->new UserDoesNotExistException("user not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

//    @Override
//    public AuthenticationResponse register(RegistrationRequest request) {
//        User user = new User();
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        request.setPassword(user.getPassword());
//
//        modelMapper.map(request,user);
//        userRepository.save(user);
//
//        RegistrationResponse response = new RegistrationResponse();
//        response.setId(user.getId());
//        return response;
//    }
}
