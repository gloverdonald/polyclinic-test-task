package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.polyclinictesttask.dto.validation.annotations.PhoneNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "account request")
public class AccountRequest {

    @NotBlank
    @Schema(description = "first name", example = "Monte")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Schema(description = "last name", example = "Lacey")
    @JsonProperty("last_name")
    private String lastName;

    @PhoneNumber
    @NotBlank
    @Schema(description = "phone")
    @JsonProperty("phone")
    private String phone;

    @NotNull
    @Schema(description = "birthdate")
    @JsonProperty("birthdate")
    private Date birthdate;
}