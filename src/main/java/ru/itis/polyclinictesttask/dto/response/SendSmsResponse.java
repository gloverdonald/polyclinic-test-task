package ru.itis.polyclinictesttask.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendSmsResponse {
    Boolean success;

    List<SmsResponseModel> data;

    String message;
}
