package ru.itis.polyclinictesttask.utils;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.polyclinictesttask.exception.EmailNotSendException;
import ru.itis.polyclinictesttask.model.AccountEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailRegistrationNotificationUtil implements RegistrationNotificationUtil {
    private final JavaMailSender mailSender;

    @Value("${email.confirm.url}")
    private String confirmUrl;

    private final FreeMarkerConfigurer freeMarkerConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    public String sendConfirmNotification(AccountEntity account) {
        String newConfirmCode = UUID.randomUUID().toString();
        Map<String, Object> data = new HashMap<>();
        data.put("user", account);
        data.put("confirmLink", confirmUrl + newConfirmCode);
        try {
            log.info("Email send {} to {}", "confirmation", account.getEmail());
            String mailTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfiguration.getConfiguration().getTemplate("confirm_mail" + ".ftl"), data);
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject("confirmation");
                messageHelper.setText(mailTemplate, true);
                messageHelper.setTo(account.getEmail());
                messageHelper.setFrom(from);
            };
            mailSender.send(preparator);
        } catch (IOException | MailException | TemplateException e) {
            log.error("Email send failed", e);
            throw new EmailNotSendException();
        }
        return newConfirmCode;
    }
}
