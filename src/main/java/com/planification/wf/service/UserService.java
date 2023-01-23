package com.planification.wf.service;


import com.planification.wf.DTO.AuthenticationResponseDTO;
import com.planification.wf.DTO.AuthenticationRequestDTO;
import com.planification.wf.DTO.RegisterRequestDTO;
import com.planification.wf.entity.User;
import com.planification.wf.exceptions.EmailAlreadyExistsException;
import com.planification.wf.exceptions.EmailNotFoundException;
import com.planification.wf.repository.UserRepository;
import com.planification.wf.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UserService  {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private UserRepository userRepository;
    public AuthenticationResponseDTO register(RegisterRequestDTO request)  {

        Optional<User> u = userRepository.findByEmail(request.getEmail());

        if(u.isPresent()){
            throw new EmailAlreadyExistsException();
        }else {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            System.out.println(user.getRole());
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponseDTO.builder()
                    .token(jwtToken)
                    .email(user.getEmail())
                    .build();
        }
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request)  {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow(EmailNotFoundException::new);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .email(user.getEmail())
                .token(jwtToken)
                .role(user.getRole().toString())
                .build();
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }



}
