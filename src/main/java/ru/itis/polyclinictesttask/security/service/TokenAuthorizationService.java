package ru.itis.polyclinictesttask.security.service;

import ru.itis.polyclinictesttask.dto.response.AccountResponse;

public interface TokenAuthorizationService {
    AccountResponse getUserInfoByToken(String token);

}
