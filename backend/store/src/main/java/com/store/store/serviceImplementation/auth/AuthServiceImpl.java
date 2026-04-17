package com.store.store.serviceImplementation.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store.store.dtos.auth.AuthResponse;
import com.store.store.dtos.auth.LoginRequest;
import com.store.store.dtos.auth.RefreshTokenRequest;
import com.store.store.dtos.auth.RegisterRequest;
import com.store.store.entities.User;
import com.store.store.repository.UserRepo;
import com.store.store.service.auth.AuthenticationService;
import com.store.store.service.auth.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Email already in use");

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);

        return new AuthResponse(
                jwtService.generateAccessToken(user.getEmail()),
                jwtService.generateRefreshToken(user.getEmail()));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // بيتحقق من الـ credentials — لو غلط بيرمي exception تلقائياً
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AuthResponse(
                jwtService.generateAccessToken(user.getEmail()),
                jwtService.generateRefreshToken(user.getEmail()));
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String email = jwtService.extractEmail(request.getRefreshToken());

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!jwtService.isTokenValid(request.getRefreshToken(), user.getEmail()))
            throw new RuntimeException("Invalid refresh token");

        // بنولد access token جديد بس — الـ refresh token يبقى نفسه
        return new AuthResponse(
                jwtService.generateAccessToken(user.getEmail()),
                request.getRefreshToken());
    }
}