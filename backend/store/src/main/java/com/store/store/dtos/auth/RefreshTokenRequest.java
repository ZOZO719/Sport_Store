// RefreshTokenRequest.java
package com.store.store.dtos.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}