package ru.itis.polyclinictesttask.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.request.TokenRefreshRequest;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.dto.response.TokensResponse;
import ru.itis.polyclinictesttask.exception.UserNotFoundException;
import ru.itis.polyclinictesttask.model.AccountEntity;
import ru.itis.polyclinictesttask.model.RefreshTokenEntity;
import ru.itis.polyclinictesttask.repository.AccountRepository;
import ru.itis.polyclinictesttask.repository.RefreshTokenRepository;
import ru.itis.polyclinictesttask.service.TokenService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final AccountRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${expiration.refresh}")
    private Long refreshTokenLifetime;
    @Value("${expiration.access}")
    private Long accessTokenLifetime;
    @Value("${secret}")
    private String secretKey;

    @Override
    public TokensResponse generateTokens(AccountResponse userResponse) {
        AccountEntity userEntity = userRepository
                .findById(userResponse.getId())
                .orElseThrow(UserNotFoundException::new);

        RefreshTokenEntity refreshToken = generateRefreshToken(userEntity);
        refreshTokenRepository.save(refreshToken);

        return TokensResponse.builder()
                .accessToken("BEARER ".concat(generateAccessToken(userEntity)))
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public TokensResponse refreshTokens(TokenRefreshRequest request) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(UserNotFoundException::new);
        AccountEntity userEntity = refreshTokenEntity.getAccount();
        refreshTokenEntity.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshTokenEntity);
        return TokensResponse.builder()
                .accessToken("BEARER ".concat(generateAccessToken(userEntity)))
                .refreshToken(refreshTokenEntity.getToken())
                .build();
    }

    private RefreshTokenEntity generateRefreshToken(AccountEntity userEntity) {
        return RefreshTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(refreshTokenLifetime, ChronoUnit.MILLIS))
                .account(userEntity)
                .build();
    }

    private String generateAccessToken(AccountEntity userEntity) {
        Map<String, Object> claims = Jwts.claims();
        claims.put(Claims.SUBJECT, userEntity.getEmail());
        return Jwts.builder()
                .setSubject(userEntity.getEmail())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(accessTokenLifetime)))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}