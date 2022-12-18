package ru.itis.polyclinictesttask.utils;

import ru.itis.polyclinictesttask.model.AccountEntity;
import ru.itis.polyclinictesttask.model.AppointmentEntity;

public interface AppointmentNotificationUtil {
    void sendAppointmentNotification(AppointmentEntity appointment, AccountEntity account);
}