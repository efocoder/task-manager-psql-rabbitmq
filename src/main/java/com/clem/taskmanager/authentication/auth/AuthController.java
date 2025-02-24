package com.clem.taskmanager.authentication.auth;

import com.clem.taskmanager.authentication.auth.dto.LoginDto;
import com.clem.taskmanager.authentication.auth.dto.RegistrationDto;
import com.clem.taskmanager.authentication.security.JwtResponse;
import com.clem.taskmanager.authentication.security.JwtService;
import com.clem.taskmanager.shared.ApiCode;
import com.clem.taskmanager.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private ApiResponse apiResponse;
    private JwtResponse jwtResponse;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody RegistrationDto registrationDto
    ) {
        try {
            var createdUser = authService.register(registrationDto);
            apiResponse = ApiResponse.builder()
                    .code(ApiCode.SUCCESS.getCode())
                    .message("User registered successfully, Please login.")
                    .build();

            if (createdUser == null) {
                apiResponse.setCode(ApiCode.SERVER_ERROR.getCode());
                apiResponse.setMessage(ApiCode.SERVER_ERROR.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(apiResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {

        var jwtToken = authService.login(loginDto);
        Date expiry = jwtService.extractExpiration(jwtToken);
        jwtResponse = JwtResponse.builder()
                .code(ApiCode.SUCCESS.getCode())
                .message("Login successful")
                .accessToken(jwtToken)
                .tokenType("Bearer")
                .tokenExpiresAt(expiry.getTime())
                .build();

        return ResponseEntity.ok(jwtResponse);

    }

}
