package com.david.Finance_Management.service;

import com.david.Finance_Management.entity.RoleEntity;
import com.david.Finance_Management.entity.UserEntity;
import com.david.Finance_Management.exceptions.AuthExceptions.InvalidCredentialsException;
import com.david.Finance_Management.exceptions.AuthExceptions.UserAlreadyExistsException;
import com.david.Finance_Management.model.dto.UserDTO;
import com.david.Finance_Management.model.request.LoginRequest;
import com.david.Finance_Management.model.request.RegisterRequest;
import com.david.Finance_Management.model.response.LoginResponse;
import com.david.Finance_Management.model.response.RegisterResponse;
import com.david.Finance_Management.repository.RoleRepository;
import com.david.Finance_Management.repository.UserRepository;
import com.david.Finance_Management.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    /**
     * Register a new user.
     *
     * @param registerRequest contains user registration details
     * @return RegisterResponse containing user details
     */
    public RegisterResponse registerUser(RegisterRequest registerRequest) {

        // check if user already exists
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        // create new user entity instance and fetch the 'USER' role from DB
        UserEntity user = new UserEntity();
        RoleEntity userRole = roleRepository
                .findByName("USER") // by default, all users are assigned the 'USER' role, 'ADMIN' is manually assigned
                .orElseThrow(() -> new RuntimeException("Error: Role USER not found"));

        user.setFirst_name(registerRequest.getFirst_name());
        user.setLast_name(registerRequest.getLast_name());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Collections.singleton(userRole));
        user.setCreated_at(LocalDateTime.now());

        // save the new user and return a RegisterResponse as response
        UserEntity savedUser = userRepository.save(user);
        return new RegisterResponse(UserDTO.mapToDTO(savedUser));
    }

    /**
     * Authenticate the user and generate a JWT token.
     *
     * @param loginRequest the login request containing username and password
     * @return LoginResponse containing user details and JWT token
     */
    public LoginResponse loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            if (authentication.isAuthenticated()) {
                String jwtToken = jwtService.generateToken(loginRequest.getEmail());

                UserEntity user = userRepository
                        .findByEmail(loginRequest.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return new LoginResponse(UserDTO.mapToDTO(user), jwtToken);
            }
            throw new InvalidCredentialsException("Authentication failed");
        }
        catch (Exception e) {
            throw new InvalidCredentialsException("Authentication failed: " + e.getMessage());
        }
    }
}
