package ru.itis.polyclinictesttask.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "appointment response")
public class AppointmentResponse {
    @Schema(description = "doctor")
    @JsonProperty("status")
    private String status;

    @Schema(description = "doctor")
    @JsonProperty("doctor_id")
    private UUID doctorId;

    @Schema(description = "doctor")
    @JsonProperty("medical_care_id")
    private UUID medicalCareId;

    @Schema(description = "patient")
    @JsonProperty("patient_id")
    private UUID patientId;

    @Schema(description = "commentary")
    @JsonProperty("commentary")
    private String commentary;

    @Schema(description = "anamnesis")
    @JsonProperty("anamnesis")
    private String anamnesis;

    @Schema(description = "startTime")
    @JsonProperty("startTime")
    private OffsetDateTime startTime;

    @Schema(description = "endTime")
    @JsonProperty("endTime")
    private OffsetDateTime endTime;
}
