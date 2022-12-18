package ru.itis.polyclinictesttask.service;

import ru.itis.polyclinictesttask.dto.request.*;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.dto.response.FileResponse;
import ru.itis.polyclinictesttask.model.AccountEntity;
import ru.itis.polyclinictesttask.security.details.UserDetailsImpl;

import java.util.UUID;

public interface AccountService {
    AccountResponse createUser(SignUpRequest signUpRequest);

    AccountResponse login(SignInRequest signInRequest);

    AccountResponse confirm(String confirmCode, NewPasswordRequest passwordRequest);

    AccountResponse getById(UUID id);

    FileResponse getAvatarByUserId(UUID id);

    AccountResponse updateUser(UUID userId, AccountRequest userRequest);

    AccountEntity save(AccountEntity userEntity);

    void updatePassword(PasswordUpdateRequest passwordUpdateRequest, UserDetailsImpl userDetailsImpl);
}
