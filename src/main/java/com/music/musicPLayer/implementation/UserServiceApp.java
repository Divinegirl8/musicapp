package com.music.musicPLayer.implementation;

import com.music.musicPLayer.dtos.request.RegistrationRequest;
import com.music.musicPLayer.dtos.response.RegistrationResponse;
import com.music.musicPLayer.data.model.User;
import com.music.musicPLayer.data.repository.UserRepository;
import com.music.musicPLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceApp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistrationResponse register(RegistrationRequest request) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setPassword(user.getPassword());

        modelMapper.map(request,user);
        userRepository.save(user);

        RegistrationResponse response = new RegistrationResponse();
        response.setId(user.getId());
        return response;
    }
}
