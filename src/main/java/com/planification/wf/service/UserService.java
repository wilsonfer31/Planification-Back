package com.planification.wf.service;


import com.planification.wf.mappers.UserMapper;
import com.planification.wf.models.DTO.AuthenticationRequestDTO;
import com.planification.wf.models.DTO.AuthenticationResponseDTO;
import com.planification.wf.models.DTO.RegisterRequestDTO;
import com.planification.wf.models.DTO.UserDTO;
import com.planification.wf.models.entity.User;
import com.planification.wf.models.enums.DiscordTypeMessage;
import com.planification.wf.exceptions.EmailAlreadyExistsException;
import com.planification.wf.exceptions.EmailNotFoundException;
import com.planification.wf.repository.UserRepository;
import com.planification.wf.security.JwtService;
import com.planification.wf.webhook.DiscordWebhookSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DiscordWebhookSender webhookSender;
    private final  UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, DiscordWebhookSender webhookSender, UserRepository userRepository, UserMapper userMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.webhookSender = webhookSender;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public AuthenticationResponseDTO register(RegisterRequestDTO request) throws Exception {



        Optional<User> u = userRepository.findByEmail(request.getEmail());

        if (u.isPresent()) {
            throw new EmailAlreadyExistsException();
        } else {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            repository.save(user);
            var jwtToken = jwtService.generateToken(user);

            this.webhookSender.sendWebhook("User Registration : " + request.getEmail() , DiscordTypeMessage.INFO);

            return AuthenticationResponseDTO.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow(EmailNotFoundException::new);
        var jwtToken = jwtService.generateToken(user);
        this.webhookSender.sendWebhook("User Connection : " + user.getEmail() , DiscordTypeMessage.INFO);
        return AuthenticationResponseDTO.builder()
                 .email(user.getEmail())
                .token(jwtToken)
                .build();
    }

    public List<UserDTO> getAll() {
        return this.userMapper.toListDto(userRepository.findAll());
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(EmailNotFoundException::new);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

}
