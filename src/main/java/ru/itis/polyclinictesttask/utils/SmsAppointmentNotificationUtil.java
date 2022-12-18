package ru.itis.polyclinictesttask.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.polyclinictesttask.config.SmsConfigProperties;
import ru.itis.polyclinictesttask.model.AccountEntity;
import ru.itis.polyclinictesttask.model.AppointmentEntity;
import ru.itis.polyclinictesttask.utils.client.SmsClient;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("sms")
public class SmsAppointmentNotificationUtil implements AppointmentNotificationUtil {

    private final SmsClient smsClient;

    private final SmsConfigProperties smsConfigProperties;

    @Override
    public void sendAppointmentNotification(AppointmentEntity appointment, AccountEntity account) {
        log.info("Sms send {} to {}", "appointment notification", account.getPhoneNumber());
        String timeMoment = " ".concat(String.valueOf(appointment.getStartTime().getDayOfMonth())
                .concat(" ")
                .concat(String.valueOf(appointment.getStartTime().getDayOfMonth())));
        String message = smsConfigProperties.getSmsText().concat(" ").concat(timeMoment);
        smsClient.sendSmsCode(account.getPhoneNumber(), smsConfigProperties.getSmsSign(), message);
    }
}
