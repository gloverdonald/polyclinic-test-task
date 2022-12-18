package ru.itis.polyclinictesttask.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.itis.polyclinictesttask.exception.EmailNotSendException;
import ru.itis.polyclinictesttask.model.AccountEntity;
import ru.itis.polyclinictesttask.model.AppointmentEntity;

@RequiredArgsConstructor
@Component
@Slf4j
@Profile("email")
public class EmailAppointmentNotificationUtil implements AppointmentNotificationUtil {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendAppointmentNotification(AppointmentEntity appointment, AccountEntity account) {
        try {
            log.info("Email send {} to {}", "appointment notification", account.getEmail());
            String timeMoment = " ".concat(String.valueOf(appointment.getStartTime().getDayOfMonth())
                    .concat(".")
                    .concat(String.valueOf(appointment.getStartTime().getMonth().getValue())));
            String message = "New appointment: ".concat(timeMoment);
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject("new appointment");
                messageHelper.setText(message, true);
                messageHelper.setTo(account.getEmail());
                messageHelper.setFrom(from);
            };
            mailSender.send(preparator);
        } catch (MailException e) {
            log.error("Email send failed", e);
            throw new EmailNotSendException();
        }
    }
}
