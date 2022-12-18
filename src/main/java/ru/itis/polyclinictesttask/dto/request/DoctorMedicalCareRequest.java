package ru.itis.polyclinictesttask.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "add doctor medical care request")
public class DoctorMedicalCareRequest {
    @Schema(description = "doctor id")
    @JsonProperty("doctor_id")
    private UUID doctorId;

    @Schema(description = "medical category id")
    @JsonProperty("care_id")
    private UUID careId;
}
