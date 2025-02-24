package com.clem.taskmanager.authentication.auth;

import com.clem.taskmanager.authentication.auth.dto.LoginDto;
import com.clem.taskmanager.authentication.auth.dto.RegistrationDto;
import com.clem.taskmanager.exception.UniqueConstraintViolationException;
import com.clem.taskmanager.user_management.role.RoleRepository;
import com.clem.taskmanager.authentication.security.JwtService;
import com.clem.taskmanager.shared.ApiResponse;
import com.clem.taskmanager.shared.StatusEnum;
import com.clem.taskmanager.user_management.user.User;
import com.clem.taskmanager.user_management.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Transactional
    public User register(RegistrationDto registrationDto) {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role not found"));

        var checkUserEmail = userRepository.findByEmail(registrationDto.getEmail());
        var checkUsername = userRepository.findByUsername(registrationDto.getUsername());

        if (checkUserEmail.isPresent()) throw new UniqueConstraintViolationException("Email already exists");
        if (checkUsername.isPresent()) throw new UniqueConstraintViolationException("username already exists");
        ApiResponse apiResponse;
        try {
            var user = User.builder()
                    .firstName(registrationDto.getFirstName())
                    .lastName(registrationDto.getLastName())
                    .email(registrationDto.getEmail())
                    .username(registrationDto.getUsername())
                    .password(passwordEncoder.encode(registrationDto.getPassword()))
                    .status(StatusEnum.ACTIVE)
                    .roles(List.of(userRole))
                    .build();

            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user: " + e.getMessage());
        }
    }

    public String login(LoginDto loginDto) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            var claims = new HashMap<String, Object>();
            var user = ((User) auth.getPrincipal());
//            claims.put("fullName", user.fullName());
            var jwtToken = jwtService.generateToken(claims, user);

            return jwtToken;
        } catch (Exception e) {
            throw new BadCredentialsException("");
        }
    }


}
