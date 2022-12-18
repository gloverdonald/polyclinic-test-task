package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "doctor response")
public class DoctorResponse {
    @Schema(description = "medical category id")
    @JsonProperty("id")
    private UUID id;

    @Schema(description = "first name")
    @JsonProperty("first_name")
    private String firstName;

    @Schema(description = "last name")
    @JsonProperty("last_name")
    private String lastName;

    @Schema(description = "email")
    @JsonProperty("email")
    private String email;

    @Schema(description = "education")
    @JsonProperty("education")
    private String education;

    @Schema(description = "experience")
    @JsonProperty("experience")
    private Integer experience;
}
