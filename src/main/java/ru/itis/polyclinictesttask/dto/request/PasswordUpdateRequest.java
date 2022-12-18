package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.polyclinictesttask.dto.validation.annotations.CustomPassword;
import ru.itis.polyclinictesttask.dto.validation.annotations.OldNotSamePasswords;
import ru.itis.polyclinictesttask.dto.validation.annotations.SamePasswords;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SamePasswords
@OldNotSamePasswords
@Schema(description = "Password Request")
public class PasswordUpdateRequest {

    @NotNull
    @Schema(description = "Old user password", example = "Password123_")
    @JsonProperty("old_password")
    private String oldPassword;

    @NotNull
    @Size(min = 8, max = 30, message = "Minimum size {min}, maximum size {max}")
    @CustomPassword
    @Schema(description = "New user password", example = "Password123_4")
    @JsonProperty("new_password")
    private String newPassword;

    @NotNull
    @Schema(description = "Repeat user password", example = "Password123_4")
    @JsonProperty("repeat_password")
    private String repeatPassword;
}