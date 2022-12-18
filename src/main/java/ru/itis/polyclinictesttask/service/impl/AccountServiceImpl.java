package ru.itis.polyclinictesttask.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.polyclinictesttask.dto.request.*;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.dto.response.FileResponse;
import ru.itis.polyclinictesttask.exception.*;
import ru.itis.polyclinictesttask.mapper.AccountMapper;
import ru.itis.polyclinictesttask.model.*;
import ru.itis.polyclinictesttask.model.enums.AccountType;
import ru.itis.polyclinictesttask.repository.AccountRepository;
import ru.itis.polyclinictesttask.repository.AdministratorRepository;
import ru.itis.polyclinictesttask.repository.DoctorRepository;
import ru.itis.polyclinictesttask.repository.PatientRepository;
import ru.itis.polyclinictesttask.security.details.UserDetailsImpl;
import ru.itis.polyclinictesttask.service.AccountService;
import ru.itis.polyclinictesttask.service.ConfirmCodeService;
import ru.itis.polyclinictesttask.service.FileService;
import ru.itis.polyclinictesttask.utils.RegistrationNotificationUtil;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final DoctorRepository doctorRepository;

    private final AdministratorRepository administratorRepository;

    private final PatientRepository patientRepository;

    private final AccountMapper accountMapper;

    private final PasswordEncoder passwordEncoder;

    private final RegistrationNotificationUtil registrationNotificationUtil;

    private final ConfirmCodeService confirmCodeService;

    private final FileService fileService;

    @Override
    public AccountResponse createUser(SignUpRequest signUpRequest) {
        log.info("User create: {}", signUpRequest.getEmail());
        accountRepository.findByEmail(signUpRequest.getEmail()).ifPresent(u -> {
            if (Boolean.TRUE.equals(u.getVerified())) {
                log.info("User already exist: {} ", signUpRequest.getEmail());
                throw new UserAlreadyExistsException();
            }
        });
        AccountEntity account = accountRepository.findByEmail(signUpRequest.getEmail()).
                orElse(accountMapper.toEntity(signUpRequest));
        String newConfirmCode = registrationNotificationUtil.sendConfirmNotification(account);
        account.setConfirmCode(ConfirmCodeEntity.builder().code(newConfirmCode).build());
        account.setHashPassword("NOT VERIFIED");
        if (account.getDoctor() == null && account.getPatient() == null && account.getAdministratorEntity() == null) {
            if (account.getAccountType().equals(AccountType.DOCTOR)) {
                doctorRepository.save(DoctorEntity.builder().account(account).build());
            } else if (account.getAccountType().equals(AccountType.ADMINISTRATOR)) {
                administratorRepository.save(AdministratorEntity.builder().account(account).build());
            } else if (account.getAccountType().equals(AccountType.PATIENT)) {
                patientRepository.save(PatientEntity.builder().account(account).build());
            } else {
                throw new AccountTypeNotRecognizeException();
            }
        } else {
            accountRepository.save(account);
        }
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountResponse login(SignInRequest signInRequest) {
        log.info("User login: {}", signInRequest.getEmail());
        AccountEntity user = accountRepository.findByEmail(signInRequest.getEmail())
                .filter(u -> passwordEncoder.matches(signInRequest.getPassword(), u.getHashPassword()))
                .orElseThrow(() -> new UnauthorizedException("Can't login in: " + signInRequest.getEmail() + ". Wrong email or password."));
        if (!user.getVerified()) {
            log.warn("User is not confirmed");
            throw new ConfirmEmailException();
        }
        return accountMapper.toResponse(user);
    }

    @Override
    public AccountResponse confirm(String confirmCode, NewPasswordRequest passwordRequest) {
        ConfirmCodeEntity confirmCodeEntity = confirmCodeService.getEntityByConfirmCode(confirmCode);
        AccountEntity user = confirmCodeEntity.getAccount();
        log.info("User confirm: {}", user.getEmail());
        user.setHashPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        user.setVerified(true);
        return accountMapper.toResponse(accountRepository.save(user));
    }

    @Override
    public AccountResponse getById(UUID id) {
        log.info("User get: {}", id.toString());
        return accountMapper.toResponse(accountRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public FileResponse getAvatarByUserId(UUID id) {
        log.info("User get avatar: {}", id.toString());
        FileEntity avatar = accountRepository.findById(id).orElseThrow(UserNotFoundException::new).getAvatar();
        if (avatar == null) {
            throw new FileNotFoundException();
        }
        return fileService.getResponseById(avatar.getId());
    }

    @Override
    public AccountResponse updateUser(UUID userId, AccountRequest userRequest) {
        AccountEntity user = accountRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        log.info("User update: {}", user.getEmail());
        accountMapper.update(user, userRequest);
        return accountMapper.toResponse(accountRepository.save(user));
    }

    @Override
    public AccountEntity save(AccountEntity userEntity) {
        log.info("User save: {}", userEntity.getEmail());
        return accountRepository.save(userEntity);
    }

    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest, UserDetailsImpl userDetailsImpl) {
        AccountEntity user = userDetailsImpl.getAccount();
        log.info("User update password: {}", user.getEmail());
        if (!passwordEncoder.matches(passwordUpdateRequest.getOldPassword(), user.getHashPassword())) {
            throw new WrongOldPasswordException();
        }
        user.setHashPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        accountRepository.save(user);
    }
}
