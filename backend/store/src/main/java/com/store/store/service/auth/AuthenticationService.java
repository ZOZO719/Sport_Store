package com.store.store.service.auth;

import com.store.store.dtos.auth.AuthResponse;
import com.store.store.dtos.auth.LoginRequest;
import com.store.store.dtos.auth.RefreshTokenRequest;
import com.store.store.dtos.auth.RegisterRequest;

public interface AuthenticationService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}