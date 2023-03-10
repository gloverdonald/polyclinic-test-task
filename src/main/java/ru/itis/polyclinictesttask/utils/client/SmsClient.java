package ru.itis.polyclinictesttask.utils.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.polyclinictesttask.dto.response.SendSmsResponse;

@ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
@FeignClient(value = "smsAero", url = "https://${sms.email}:${sms.api-key}@gate.smsaero.ru/v2")
public interface SmsClient {

    @GetMapping("/sms/send")
    SendSmsResponse sendSmsCode(@RequestParam(name = "number") String number,
                                @RequestParam(name = "sign") String sign,
                                @RequestParam(name = "text") String text);


}
