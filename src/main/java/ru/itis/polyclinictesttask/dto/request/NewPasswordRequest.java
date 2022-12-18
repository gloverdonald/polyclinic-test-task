package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.polyclinictesttask.dto.validation.annotations.CustomPassword;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Password Request")
public class NewPasswordRequest {

    @CustomPassword
    @Schema(description = "User password", example = "Password123_")
    @JsonProperty("password")
    private String password;
}