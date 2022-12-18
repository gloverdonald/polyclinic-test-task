package ru.itis.polyclinictesttask.utils;

import ru.itis.polyclinictesttask.model.AccountEntity;

public interface RegistrationNotificationUtil {
    String sendConfirmNotification(AccountEntity account);
}
