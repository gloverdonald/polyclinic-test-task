package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Doctor Request")
public class DoctorRequest {
    @Schema(description = "education")
    @JsonProperty("education")
    private String education;

    @Schema(description = "experience")
    @JsonProperty("experience")
    private Integer experience;
}
