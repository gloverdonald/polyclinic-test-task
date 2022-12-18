package ru.itis.polyclinictesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.polyclinictesttask.api.AuthApi;
import ru.itis.polyclinictesttask.dto.request.NewPasswordRequest;
import ru.itis.polyclinictesttask.dto.request.SignInRequest;
import ru.itis.polyclinictesttask.dto.request.SignUpRequest;
import ru.itis.polyclinictesttask.dto.request.TokenRefreshRequest;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.dto.response.TokensResponse;
import ru.itis.polyclinictesttask.service.AccountService;
import ru.itis.polyclinictesttask.service.TokenService;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AccountService accountService;

    private final TokenService tokenService;

    @Override
    public ResponseEntity<AccountResponse> createUser(SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createUser(signUpRequest));
    }

    @Override
    public ResponseEntity<TokensResponse> login(SignInRequest signInRequest) {
        return ResponseEntity.ok(tokenService.generateTokens(accountService.login(signInRequest)));
    }

    @Override
    public ResponseEntity<TokensResponse> confirm(String code, NewPasswordRequest newPasswordRequest) {
        return ResponseEntity.ok(tokenService.generateTokens(accountService.confirm(code, newPasswordRequest)));
    }

    @Override
    public ResponseEntity<TokensResponse> refresh(TokenRefreshRequest request) {
        return ResponseEntity.ok(tokenService.refreshTokens(request));
    }
}