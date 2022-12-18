package ru.itis.polyclinictesttask.service;

import ru.itis.polyclinictesttask.dto.request.TokenRefreshRequest;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.dto.response.TokensResponse;

public interface TokenService {
    TokensResponse generateTokens(AccountResponse accountResponse);

    TokensResponse refreshTokens(TokenRefreshRequest request);
}
