package ru.itis.polyclinictesttask.security.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.exception.UserNotFoundException;
import ru.itis.polyclinictesttask.mapper.AccountMapper;
import ru.itis.polyclinictesttask.repository.AccountRepository;
import ru.itis.polyclinictesttask.security.service.TokenAuthorizationService;

@RequiredArgsConstructor
@Service
public class TokenAuthorizationServiceImpl implements TokenAuthorizationService {

    @Value("${secret}")
    private String secretKey;

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountResponse getUserInfoByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        String email = claims.getSubject();
        return accountMapper.toResponse(accountRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new));
    }
}
